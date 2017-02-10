package models

import com.google.inject.Inject
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.driver.H2Driver

import scala.concurrent.Future

class UserDAO @Inject() (protected val dbConfigProvider: DatabaseConfigProvider) extends UserTableDef with HasDatabaseConfigProvider[H2Driver] {
  import driver.api._

  def add(user: User): Future[String] = {
    db.run(users += user).map(_ => "User successfully added").recover {
      case e: Exception => e.getCause.getMessage
    }
  }

  def delete(uuid: Long): Future[Int] = dbConfig.db.run(users.filter(_.uuid === uuid).delete)

  def get(uuid: Long): Future[Option[User]] = dbConfig.db.run(users.filter(_.uuid === uuid).result.headOption)
}