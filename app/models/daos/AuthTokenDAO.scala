package models.daos

import java.util.UUID

import models.AuthToken
import org.joda.time.DateTime

import scala.concurrent.Future

/**
  * An API that gives access to the [[AuthToken]] object.
  */
trait AuthTokenDAO {

  /**
    * Finds a token by its ID.
    *
    * @param uuid The unique token ID.
    * @return The found token or None if no token for the given ID could be found.
    */
  def find(uuid: UUID): Future[Option[AuthToken]]

  /**
    * Finds expired tokens.
    *
    * @param dateTime The current date time.
    * @return A Seq of all expired tokens.
    */
  def findExpired(dateTime: DateTime): Future[Seq[AuthToken]]

  /**
    * Saves a token.
    *
    * @param token The token to be saved.
    * @return The saved token.
    */
  def save(token: AuthToken): Future[AuthToken]

  /**
    * Removes the token for the given ID.
    *
    * @param uuid The ID for which the token should be removed.
    * @return A future to wait for the process to be completed.
    */
  def remove(uuid: UUID): Future[Unit]
}
