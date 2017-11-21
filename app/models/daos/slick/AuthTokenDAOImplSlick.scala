package models.daos.slick

import java.util.UUID
import javax.inject.Inject

import models.AuthToken
import models.daos.AuthTokenDAO
import org.joda.time.DateTime
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.H2Profile

import scala.concurrent.Future

class AuthTokenDAOImplSlick @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)
  extends AuthTokenDAO with AuthTokenTableDefSlick with HasDatabaseConfigProvider[H2Profile] {

  //import profile.api._

  def find(uuid: UUID) = ???

  def findExpired(dateTime: DateTime): Future[Seq[AuthToken]] = ???

  def save(token: AuthToken): Future[AuthToken] = ???

  def remove(uuid: UUID): Future[Unit] = ???
}
