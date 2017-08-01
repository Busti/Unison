package models.daos.slick

import java.sql.Timestamp
import java.util.UUID

import slick.jdbc.H2Profile
import slick.lifted.ProvenShape.proveShapeOf

trait AuthTokenTableDefSlick {
  protected val profile: H2Profile
  import profile.api._

  case class DBAuthToken(uuid: UUID, userId: UUID, expiry: Timestamp)

  class AuthTokens(tag: Tag) extends Table[DBAuthToken](tag, "AuthTokens") {
    def uuid   = column[UUID]("uuid", O.PrimaryKey)
    def userId = column[UUID]("userId")
    def expiry = column[Timestamp]("expiry")
    def *      = (uuid, userId, expiry) <> (DBAuthToken.tupled, DBAuthToken.unapply)
  }

  val authTokens = TableQuery[AuthTokens]
}
