package controllers.auth

import javax.inject.Inject

import com.mohiva.play.silhouette.api._
import com.mohiva.play.silhouette.api.repositories.AuthInfoRepository
import com.mohiva.play.silhouette.api.services.AvatarService
import com.mohiva.play.silhouette.api.util.PasswordHasherRegistry
import controllers.AssetsFinder
import models.services.{AuthTokenService, UserService}
import org.webjars.play.WebJarsUtil
import play.api.i18n.I18nSupport
import play.api.mvc.{AbstractController, AnyContent, ControllerComponents, Request}
import utils.silhouette.EnvDefault

import scala.concurrent.ExecutionContext

class ControllerSignUp @Inject()
(
  components: ControllerComponents,
  silhouette: Silhouette[EnvDefault],
  userService: UserService,
  authInfoRepository: AuthInfoRepository,
  authTokenService: AuthTokenService,
  avatarService: AvatarService,
  passwordHasherRegistry: PasswordHasherRegistry,
)(
  implicit
  webJarsUtil: WebJarsUtil,
  assets: AssetsFinder,
  ex: ExecutionContext
) extends AbstractController(components) with I18nSupport {

  def view = Action { implicit request =>
    Ok(views.html.auth.signup())
  }

  def submit = silhouette.UnsecuredAction.async { implicit request: Request[AnyContent] =>
    ???
  }
}
