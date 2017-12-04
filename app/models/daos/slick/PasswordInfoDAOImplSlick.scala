package models.daos.slick

import javax.inject.Inject

import com.mohiva.play.silhouette.api.LoginInfo
import com.mohiva.play.silhouette.api.util.PasswordInfo
import com.mohiva.play.silhouette.persistence.daos.DelegableAuthInfoDAO
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.H2Profile

import scala.concurrent.ExecutionContext

class PasswordInfoDAOImplSlick @Inject()
(
  protected val dbConfigProvider: DatabaseConfigProvider
)(
  implicit
  ec: ExecutionContext
) extends DelegableAuthInfoDAO[PasswordInfo] with UserTableDefSlick with HasDatabaseConfigProvider[H2Profile] {
  import profile.api._

  /**
    * Generates a query for a DBPasswordInfo linked to the given LoginInfo.
    * @param loginInfo The linked Login info.
    * @return The query that might yield the DBPasswordInfo when executed.
    */
  protected def passwordInfoQuery(loginInfo: LoginInfo) = for {
    dbLoginInfo <- loginInfoQuery(loginInfo)
    dbPasswordInfo <- passwordInfos if dbPasswordInfo.loginInfoId === dbLoginInfo.id
  } yield dbPasswordInfo

  /**
    * Use subquery workaround instead of join to get passwordInfo because slick only supports selecting
    * from a single table for update/delete queries (https://github.com/slick/slick/issues/684).
    * We do this so that we do not have to generate a second request after retrieving LoginInfo's ID
    * @param loginInfo The login info Linked in the passwordInfo
    * @return The linked PasswordInfo query.
    */
  //Todo: 05.12.2017 Make this unnecessary. See linked issue.
  protected def passwordInfoSubQuery(loginInfo: LoginInfo) =
    passwordInfos.filter(_.loginInfoId in loginInfoQuery(loginInfo).map(_.id))

  /**
    * Generates an Action that adds a DBPasswordInfo to the PasswordInfos Table.
    * @param loginInfo The LoginInfo Linked to the PasswordInfo.
    * @param passwordInfo The PasswordInfo to be added.
    * @return A Database action that action that adds the specified PasswordInfo.
    */
  protected def addAction(loginInfo: LoginInfo, passwordInfo: PasswordInfo) =
    loginInfoQuery(loginInfo).result.head.flatMap { dbLoginInfo =>
      passwordInfos +=
        DBPasswordInfo(passwordInfo.hasher, passwordInfo.password, passwordInfo.salt, dbLoginInfo.id.get)
    }.transactionally

  /**
    * Generates an Action that updates a PasswordInfo.
    * @param loginInfo The loginInfo the PasswordInfo is Linked to.
    * @param passwordInfo The updated PasswordInfo.
    * @return The Action that updates the specified password Info.
    */
  protected def updateAction(loginInfo: LoginInfo, passwordInfo: PasswordInfo) =
    passwordInfoSubQuery(loginInfo).
      map(dbPasswordInfo => (dbPasswordInfo.hasher, dbPasswordInfo.hash, dbPasswordInfo.salt)).
      update((passwordInfo.hasher, passwordInfo.password, passwordInfo.salt))

  override def find(loginInfo: LoginInfo) = ???
  override def add(loginInfo: LoginInfo, authInfo: PasswordInfo) = ???
  override def update(loginInfo: LoginInfo, authInfo: PasswordInfo) = ???
  override def save(loginInfo: LoginInfo, authInfo: PasswordInfo) = ???
  override def remove(loginInfo: LoginInfo) = ???
}
