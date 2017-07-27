package models.services

import java.util.UUID
import javax.inject.Inject

import com.mohiva.play.silhouette.api.LoginInfo
import models.User
import models.daos.slick.UserDAOImplSlick

/**
  * Handles actions to users.
  * An implementation of the UserService API that uses Slick
  */
class UserServiceImplSlick @Inject() (userDAO: UserDAOImplSlick) extends UserService {
  override def retrieve(loginInfo: LoginInfo) = userDAO.find(loginInfo)
  override def retrieve(id: UUID) = userDAO.find(id)
  override def save(user: User) = userDAO.save(user)
}
