package models

import slick.driver.H2Driver
import slick.lifted.ProvenShape

trait UserTableDef {
  protected val driver: H2Driver
  import driver.api._

  case class User(uuid: String, username: String, email: String, avatarURL: String)

  class Users(tag: Tag) extends Table[User](tag, "users") {
    def uuid      = column[String]("uuid", O.PrimaryKey)
    def username  = column[String]("username")
    def email     = column[String]("email")
    def avatarURL = column[Option[String]]("avatarURL")

    override def * : ProvenShape[User] =
      (uuid, username, email, avatarURL) <> (User.tupled, User.unapply)
  }

  case class Password(uuid:String, hasher: String, hash: String, salt: Option[String])

  class Passwords(tag: Tag) extends Table[User](tag, "passwords") {
    def uuid   = column[String]("uuid", O.PrimaryKey)
    def hasher = column[String]("hasher")
    def hash   = column[String]("hash")
    def salt   = column[Option[String]]("salt")
    override def * : ProvenShape[Password] =
      (uuid, hasher, hash, salt) <> (Password.tupled, User.unapply)
  }

  val users     = TableQuery[Users]
  val passwords = TableQuery[Passwords]
}
