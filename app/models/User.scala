package models

import play.api.Play
import play.api.db.slick.DatabaseConfigProvider
import slick.driver.H2Driver
import slick.driver.H2Driver.api._
import slick.lifted.ProvenShape

import scala.concurrent.Future

case class User(id: Long, username: String, passHash: String, passSalt: String)

class UserTableDef(tag: Tag) extends Table[User](tag, "user") {
  def id       = column[Long]("id", O.PrimaryKey, O.AutoInc)
  def username = column[String]("username")
  def passHash = column[String]("password")
  def passSalt = column[String]("salt")

  override def * : ProvenShape[User] =
    (id, username, passHash, passSalt) <>(User.tupled, User.unapply)
}

object Users {
  val dbConfig = DatabaseConfigProvider.get[H2Driver](Play.current)

  val users = TableQuery[UserTableDef]

  def add(user: User): Future[String] = {
    dbConfig.db.run(users += user).map(_ => "User successfully added").recover {
      case e: Exception => e.getCause.getMessage
    }
  }

  def delete(id: Long): Future[Int] = dbConfig.db.run(users.filter(_.id === id).delete)

  def get(id: Long): Future[Option[User]] = dbConfig.db.run(users.filter(_.id === id).result.headOption)
}