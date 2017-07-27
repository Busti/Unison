package models.services

import java.util.UUID

import models.AuthToken

import scala.concurrent.Future
import scala.concurrent.duration._

trait AuthTokenService {

  /**
    * Creates a new auth token and saves it in the backing store.
    *
    * @param userID The user ID for which the token should be created.
    * @param expiry The lifetime of a token.
    * @return The saved auth token.
    */
  def create(userID: UUID, expiry: FiniteDuration = 5 minutes): Future[AuthToken]

  /**
    * Validates a token ID.
    *
    * @param uuid The token ID to validate.
    * @return The token if it is valid, None if otherwise.
    */
  def validate(uuid: UUID): Future[Option[AuthToken]]

  /**
    * Cleans expired tokens.
    *
    * @return the list of deleted tokens.
    */
  def clean: Future[Seq[AuthToken]]
}
