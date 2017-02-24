package models

import com.google.inject.Inject
import com.mohiva.play.silhouette.api.LoginInfo
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import slick.driver.H2Driver

import scala.concurrent.Future

class UserDAO @Inject() (protected val dbConfigProvider: DatabaseConfigProvider) extends UserTableDef with HasDatabaseConfigProvider[H2Driver] {
  import driver.api._

  def loginInfoQuery(loginInfo: LoginInfo) =
    loginInfos.filter(dbLoginInfo => dbLoginInfo.providerID === loginInfo.providerID && dbLoginInfo.providerKey === loginInfo.providerKey)

  def find(loginInfo: LoginInfo): Future[Option[User]] = {
    val query = for {
      queryLoginInfo <- loginInfoQuery(loginInfo)
      queryUserLoginInfo <- userLoginInfos.filter(_.loginInfoId === queryLoginInfo.id)
      queryUser <- users.filter(_.uuid === queryUserLoginInfo.userId)
    } yield queryUser

    db.run(query.result.headOption).map { dbUserOption =>
      dbUserOption.map { user =>
        user
      }
    }
  }
}