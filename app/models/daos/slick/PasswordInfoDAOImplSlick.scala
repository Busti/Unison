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

  protected def passwordInfoQuery(loginInfo: LoginInfo) = for {
    dbLoginInfo <- loginInfoQuery(loginInfo)
  }

  override def find(loginInfo: LoginInfo) = ???
  override def add(loginInfo: LoginInfo, authInfo: PasswordInfo) = ???
  override def update(loginInfo: LoginInfo, authInfo: PasswordInfo) = ???
  override def save(loginInfo: LoginInfo, authInfo: PasswordInfo) = ???
  override def remove(loginInfo: LoginInfo) = ???
}
