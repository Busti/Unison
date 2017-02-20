package models

import slick.driver.H2Driver

trait UserTableDef {
  protected val driver: H2Driver
  import driver.api._

  case class User(uuid: String, username: String, email: String, avatarURL: Option[String])

  class Users(tag: Tag) extends Table[User](tag, "users") {
    def uuid      = column[String]("uuid", O.PrimaryKey)
    def username  = column[String]("username")
    def email     = column[String]("email")
    def avatarURL = column[Option[String]]("avatarURL")
    def *         = (uuid, username, email, avatarURL) <> (User.tupled, User.unapply)
  }

  case class Password(uuid:String, hasher: String, hash: String, salt: Option[String])

  class Passwords(tag: Tag) extends Table[Password](tag, "passwords") {
    def uuid   = column[String]("uuid", O.PrimaryKey)
    def hasher = column[String]("hasher")
    def hash   = column[String]("hash")
    def salt   = column[Option[String]]("salt")
    def *      = (uuid, hasher, hash, salt) <> (Password.tupled, Password.unapply)
  }

  case class DBLoginInfo(id: Long, providerID: String, providerKey: String)

  class LoginInfos(tag: Tag) extends Table[DBLoginInfo](tag, "logininfos") {
    def id          = column[Long]("id", O.PrimaryKey, O.AutoInc)
    def providerID  = column[String]("providerID")
    def providerKey = column[String]("providerKey")
    def *           = (id, providerID, providerKey) <> (DBLoginInfo.tupled, DBLoginInfo.unapply)
  }

  //Many to Many relation between Users and LoginInfo
  case class UserLoginInfo(userId: String, loginInfoId: Long)

  class UserLoginInfos(tag: Tag) extends Table[UserLoginInfo](tag, "userlogininfos") {
    def userId      = column[String]("userId")
    def loginInfoId = column[Long]("loginInfoId")
    def *           = (userId, loginInfoId) <> (UserLoginInfo.tupled, UserLoginInfo.unapply)
  }

  val users          = TableQuery[Users]
  val passwords      = TableQuery[Passwords]
  val loginInfos     = TableQuery[LoginInfos]
  val userLoginInfos = TableQuery[UserLoginInfos]
}
