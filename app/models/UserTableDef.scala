package models

import java.util.UUID

import slick.driver.H2Driver

trait UserTableDef {
  protected val driver: H2Driver
  import driver.api._

  case class DBUser(uuid: String, username: String, email: String, avatarURL: Option[String])

  class Users(tag: Tag) extends Table[DBUser](tag, "users") {
    def uuid      = column[UUID]("uuid", O.PrimaryKey)
    def username  = column[String]("username")
    def email     = column[String]("email")
    def avatarURL = column[Option[String]]("avatarURL")
    def *         = (uuid, username, email, avatarURL) <> (DBUser.tupled, DBUser.unapply)
  }

  case class DBPassword(uuid:String, hasher: String, hash: String, salt: Option[String])

  class Passwords(tag: Tag) extends Table[DBPassword](tag, "passwords") {
    def uuid   = column[UUID]("uuid", O.PrimaryKey)
    def hasher = column[String]("hasher")
    def hash   = column[String]("hash")
    def salt   = column[Option[String]]("salt")
    def *      = (uuid, hasher, hash, salt) <> (DBPassword.tupled, DBPassword.unapply)
  }

  case class DBLoginInfo(id: Long, providerID: String, providerKey: String)

  class LoginInfos(tag: Tag) extends Table[DBLoginInfo](tag, "logininfos") {
    def id          = column[Long]("id", O.PrimaryKey, O.AutoInc)
    def providerID  = column[String]("providerID")
    def providerKey = column[String]("providerKey")
    def *           = (id, providerID, providerKey) <> (DBLoginInfo.tupled, DBLoginInfo.unapply)
  }

  //Many to Many relation between Users and LoginInfo
  case class DBUserLoginInfo(userId: String, loginInfoId: Long)

  class UserLoginInfos(tag: Tag) extends Table[DBUserLoginInfo](tag, "userlogininfos") {
    def userId      = column[String]("userId")
    def loginInfoId = column[Long]("loginInfoId")
    def *           = (userId, loginInfoId) <> (DBUserLoginInfo.tupled, DBUserLoginInfo.unapply)
  }

  val users          = TableQuery[Users]
  val passwords      = TableQuery[Passwords]
  val loginInfos     = TableQuery[LoginInfos]
  val userLoginInfos = TableQuery[UserLoginInfos]
}
