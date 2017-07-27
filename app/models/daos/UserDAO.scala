package models.daos

import java.util.UUID

import com.mohiva.play.silhouette.api.LoginInfo
import models.User

import scala.concurrent.Future

/**
  * An API that gives access to the [[User]] object.
  */
trait UserDAO {
  /**
    * Finds a user by it's login info.
    *
    * @param loginInfo The Login Info of the User.
    * @return A Future Option populated with the user if found.
    */
  def find(loginInfo: LoginInfo): Future[Option[User]]

  /**
    * Finds a user by its id.
    *
    * @param uuid The UUID of the desired User.
    * @return A Future Option populated with the user if found.
    */
  def find(uuid: UUID): Future[Option[User]]

  /**
    * Saves a user to the database.
    *
    * @param user The user object that is being saved.
    * @return The user.
    */
  def save(user: User): Future[User]
}
