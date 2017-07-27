package controllers.auth

import java.util.UUID
import javax.inject.Inject

import com.mohiva.play.silhouette.api.repositories.AuthInfoRepository
import com.mohiva.play.silhouette.api.services.AvatarService
import com.mohiva.play.silhouette.api.util.PasswordHasherRegistry
import com.mohiva.play.silhouette.api.{LoginInfo, Silhouette}
import com.mohiva.play.silhouette.impl.providers.CredentialsProvider
import controllers.routes
import forms.FormSignUp
import models.User
import models.services.UserService
import play.api.i18n.MessagesApi
import utils.silhouette.EnvCookie

import scala.concurrent.Future

class ControllerSignUp @Inject()(
  userService: UserService,
  passwordHasherRegistry: PasswordHasherRegistry,
  avatarService: AvatarService,
  authInfoRepository: AuthInfoRepository
) extends ControllerAuth {

  def submit = silhouette.UnsecuredAction.async { implicit request =>
    //Check the retrieved form
    FormSignUp.form.bindFromRequest.fold(
      form => Future.successful(Redirect(routes.ControllerApplication.index)),
      data => {
        val result = Redirect(routes.ControllerApplication.index)
        val loginInfo = LoginInfo(CredentialsProvider.ID, data.username)
        //Try to find a user matching the retrieved data
        userService.retrieve(loginInfo).flatMap {
          case Some(user) =>
            /*
             * Send an email to the existing user and redirect to a success page.
             * The messages shown to the user attempting to create the account has to be the same as if a new account
             * had been created, for security reasons.
             */
            //Todo: Send Mail
            Future.successful(result)
          case None =>
            //Generate a new account
            val authInfo = passwordHasherRegistry.current.hash(data.password)
            //Create a new User object and fill it with the retrieved data.
            val user = User(
              uuid = UUID.randomUUID(),
              loginInfo = loginInfo,
              username = data.username,
              email = data.email,
              avatarURL = None,
              activated = false
            )
            for {
              avatar <- avatarService.retrieveURL(data.email)
              user <- userService.save(user.copy(avatarURL = avatar))
              authInfo <- authInfoRepository.add(loginInfo, authInfo)
              authToken <-
            } yield {
              ???
            }
            ???
        }
      }
    )
  }

  override def silhouette: Silhouette[EnvCookie] = ???
  override def messagesApi: MessagesApi = ???
}
