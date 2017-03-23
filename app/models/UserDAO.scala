package models

import java.util.UUID
import javax.inject.Inject

import com.mohiva.play.silhouette.api.LoginInfo
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import slick.dbio.DBIOAction
import slick.driver.H2Driver

import scala.concurrent.Future

class UserDAO @Inject()(protected val dbConfigProvider: DatabaseConfigProvider) extends UserTableDef with HasDatabaseConfigProvider[H2Driver] {

  import driver.api._

  def loginInfoQuery(loginInfo: LoginInfo) =
    loginInfos.filter(dbLoginInfo => dbLoginInfo.providerID === loginInfo.providerID && dbLoginInfo.providerKey === loginInfo.providerKey)

  /**
    * Find a user by it's login info.
    *
    * @param loginInfo The Login Info of the User.
    * @return A Future Option populated with the user if found.
    */
  def find(loginInfo: LoginInfo): Future[Option[User]] = {
    val query = for {
      queryLoginInfo <- loginInfoQuery(loginInfo)
      queryUserLoginInfo <- userLoginInfos.filter(_.loginInfoId === queryLoginInfo.id)
      queryUser <- users.filter(_.uuid === queryUserLoginInfo.userId)
    } yield queryUser

    db.run(query.result.headOption).map { dbUserOption =>
      dbUserOption.map { user =>
        new User(loginInfo, user.uuid, user.username, user.email, user.avatarURL)
      }
    }
  }

  /**
    * Find a user by its id.
    *
    * @param id The UUID of the desired User.
    * @return A Future Option populated with the user if found.
    */
  def find(id: UUID): Future[Option[User]] = {
    val query = for {
      queryUser <- users.filter(_.uuid === id)
      queryUserLoginInfo <- userLoginInfos.filter(_.userId === queryUser.uuid)
      queryLoginInfo <- loginInfos.filter(_.id === queryUserLoginInfo.loginInfoId)
    } yield (queryUser, queryLoginInfo)

    db.run(query.result.headOption).map { resultOption =>
      resultOption.map {
        case (user, loginInfo) =>
          new User(
            LoginInfo(loginInfo.providerID, loginInfo.providerKey),
            user.uuid, user.username, user.email, user.avatarURL
          )
      }
    }

    def save(user: User) = {
      val dbUser = DBUser(user.uuid, user.username, user.email, user.avatarURL)
      val dBLoginInfo = DBLoginInfo(None, user.loginInfo.providerID, user.loginInfo.providerKey)

      val loginInfoAction = {
        val retrieveLoginInfo = loginInfos.filter(
          info =>
            info.providerID === user.loginInfo.providerID
            &&
            info.providerKey === user.loginInfo.providerKey
        ).result.headOption

        val insertLoginInfo = loginInfos.returning(loginInfos.map(_.id)).into(
          (info, id) =>
            info.copy(id = Some(id))
        ) += dBLoginInfo

        for {
          loginInfoOption <- retrieveLoginInfo
          loginInfo <- loginInfoOption.map(DBIO.successful(_)).getOrElse(insertLoginInfo)
        } yield loginInfo

        val actions = (for {
          _ <- users.insertOrUpdate(dbUser)
          loginInfo <- loginInfoAction
          _ <- userLoginInfos += DBUserLoginInfo(dbUser.uuid, loginInfo.id.get)
        } yield ()).transactionally

        db.run(actions).map(_ => user)
      }
    }
  }
}