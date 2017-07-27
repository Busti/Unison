package models.daos.slick

import java.util.UUID

import org.joda.time.DateTime
import slick.driver.H2Driver

trait AuthTokenTableDefSlick {
  protected val driver: H2Driver
  import driver.api._

  case class DBAuthToken(id: UUID, userId: UUID, expiry: DateTime)

  class AuthTokens(tag: Tag) extends Table[DBAuthToken](tag, "AuthTokens") {
    def uuid   = column[UUID]("uuid", O.PrimaryKey)
    def userId = column[UUID]("userId")
    def expiry = column[DateTime]("expiry")
    def * = (uuid, userId, expiry) <> (DBAuthToken.tupled, DBAuthToken.unapply)
  }

  val authTokens = TableQuery[AuthTokens]
}
