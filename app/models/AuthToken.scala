package models

import java.util.UUID

import org.joda.time.DateTime

/**
  * A token to authenticate a user against an endpoint for a short time period.
  *
  * @param uuid   The unique token ID.
  * @param userID The unique ID of the user the token is associated with.
  * @param expiry The date-time the token expires.
  */
case class AuthToken (
  uuid: UUID,
  userID: UUID,
  expiry: DateTime
)
