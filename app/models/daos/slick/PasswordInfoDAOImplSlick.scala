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

  protected def passwordInfoQuery(loginInfo: LoginInfo) = for {
    dbLoginInfo <- loginInfoQuery(loginInfo)
    dbPasswordInfo <- passwords if dbPasswordInfo.loginInfoId === dbLoginInfo.id
  } yield dbPasswordInfo

  // Use subquery workaround instead of join to get authinfo because slick only supports selecting
  // from a single table for update/delete queries (https://github.com/slick/slick/issues/684).
  protected def passwordInfoSubQuery(loginInfo: LoginInfo) =
    passwords.filter(_.loginInfoId in loginInfoQuery(loginInfo).map(_.id))


  protected def addAction(loginInfo: LoginInfo, passwordInfo: PasswordInfo) =
    loginInfoQuery(loginInfo).result.head.flatMap { dbLoginInfo =>
      passwords +=
        DBPasswordInfo(passwordInfo.hasher, passwordInfo.password, passwordInfo.salt, dbLoginInfo.id.get)
    }.transactionally

  override def find(loginInfo: LoginInfo) = ???
  override def add(loginInfo: LoginInfo, authInfo: PasswordInfo) = ???
  override def update(loginInfo: LoginInfo, authInfo: PasswordInfo) = ???
  override def save(loginInfo: LoginInfo, authInfo: PasswordInfo) = ???
  override def remove(loginInfo: LoginInfo) = ???
}
