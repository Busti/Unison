package utils.silhouette

import javax.inject.Inject

import com.mohiva.play.silhouette.api.LoginInfo
import com.mohiva.play.silhouette.api.services.IdentityService
import models.User
import models.daos.slick.UserDAOImplSlick

import scala.concurrent.Future

class UserService @Inject()(userDao: UserDAOImplSlick) extends IdentityService[User] {
  def retrieve(loginInfo: LoginInfo): Future[Option[User]] =
    userDao.find(loginInfo)

  def save(user: User): Future[User] =
    userDao.save(user)
}
