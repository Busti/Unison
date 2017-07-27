package controllers.auth

import com.mohiva.play.silhouette.api.Silhouette
import com.mohiva.play.silhouette.api.actions.{SecuredRequest, UserAwareRequest}
import models.User
import play.api.i18n.I18nSupport
import play.api.mvc.Controller
import utils.silhouette.EnvCookie

trait ControllerAuth extends Controller with I18nSupport {
  def silhouette: Silhouette[EnvCookie]
  def env = silhouette.env

  def SecuredAction = silhouette.SecuredAction
  def UnsecuredAction = silhouette.UnsecuredAction
  def UserAwareAction = silhouette.UserAwareAction

  implicit def securedRequest2User[A](implicit request: SecuredRequest[EnvCookie, A]): User = request.identity
  implicit def userAwareRequest2UserOpt[A](implicit request: UserAwareRequest[EnvCookie, A]): Option[User] = request.identity
}
