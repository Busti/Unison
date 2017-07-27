package models.services

import java.util.UUID

import com.mohiva.play.silhouette.api.services.IdentityService
import models.User

import scala.concurrent.Future

/**
  * An API that handles actions to users.
  */
trait UserService extends IdentityService[User] {

  /**
    * Retrieves a user that matches the specified ID.
    *
    * @param uuid The ID to retrieve a user.
    * @return The retrieved user or None if no user could be retrieved for the given ID.
    */
  def retrieve(uuid: UUID): Future[Option[User]]

  /**
    * Saves a user.
    *
    * @param user The user to save.
    * @return The saved user.
    */
  def save(user: User): Future[User]

  /*
  /**
    * Saves the social profile for a user.
    *
    * If a user exists for this profile then update the user, otherwise create a new user with the given profile.
    *
    * @param profile The social profile to save.
    * @return The user for whom the profile was saved.
    */
  def save(profile: CommonSocialProfile): Future[Option[User]]
  */
}
