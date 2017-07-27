package models.daos.slick

import java.util.UUID

import slick.driver.H2Driver
import slick.lifted.ProvenShape.proveShapeOf

trait UserTableDefSlick {
  //Todo: 26.07.2017 Use Postgres in production.
  //Todo: 26.07.2017 Make the driver easily interchangeable?
  protected val driver: H2Driver
  import driver.api._

  case class DBUser(uuid: UUID, username: String, email: String, avatarURL: Option[String], activated: Boolean)

  class Users(tag: Tag) extends Table[DBUser](tag, "Users") {
    def uuid      = column[UUID]("uuid", O.PrimaryKey)
    def username  = column[String]("username")
    def email     = column[String]("email")
    def avatarURL = column[Option[String]]("avatarURL")
    def activated = column[Boolean]("activated")
    def *         = (uuid, username, email, avatarURL, activated) <> (DBUser.tupled, DBUser.unapply)
  }

  case class DBPassword(uuid:UUID, hasher: String, hash: String, salt: Option[String])

  class Passwords(tag: Tag) extends Table[DBPassword](tag, "Passwords") {
    def uuid   = column[UUID]("uuid", O.PrimaryKey)
    def hasher = column[String]("hasher")
    def hash   = column[String]("hash")
    def salt   = column[Option[String]]("salt")
    def *      = (uuid, hasher, hash, salt) <> (DBPassword.tupled, DBPassword.unapply)
  }

  case class DBLoginInfo(id: Option[Long], providerID: String, providerKey: String)

  class LoginInfos(tag: Tag) extends Table[DBLoginInfo](tag, "LoginInfos") {
    def id          = column[Long]("id", O.PrimaryKey, O.AutoInc)
    def providerID  = column[String]("providerID")
    def providerKey = column[String]("providerKey")
    def *           = (id.?, providerID, providerKey) <> (DBLoginInfo.tupled, DBLoginInfo.unapply)
  }

  //Many to Many relation between Users and LoginInfo
  case class DBUserLoginInfo(userId: UUID, loginInfoId: Long)

  class UserLoginInfos(tag: Tag) extends Table[DBUserLoginInfo](tag, "User_LoginInfo") {
    def userId      = column[UUID]("uuid_user")
    def loginInfoId = column[Long]("id_LoginInfo")
    def *           = (userId, loginInfoId) <> (DBUserLoginInfo.tupled, DBUserLoginInfo.unapply)
  }

  //Access Objects for the Tables
  val users          = TableQuery[Users]
  val passwords      = TableQuery[Passwords]
  val loginInfos     = TableQuery[LoginInfos]
  val userLoginInfos = TableQuery[UserLoginInfos]
}
