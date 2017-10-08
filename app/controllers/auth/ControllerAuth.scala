package controllers.auth

import com.mohiva.play.silhouette.api.Silhouette
import com.mohiva.play.silhouette.api.actions.{SecuredRequest, UserAwareRequest}
import models.User
import play.api.i18n.I18nSupport
import play.api.mvc.BaseController
import utils.silhouette.EnvDefault

trait ControllerAuth extends BaseController with I18nSupport {
  def silhouette: Silhouette[EnvDefault]
  def env = silhouette.env

  def SecuredAction = silhouette.SecuredAction
  def UnsecuredAction = silhouette.UnsecuredAction
  def UserAwareAction = silhouette.UserAwareAction

  implicit def securedRequest2User[A](implicit request: SecuredRequest[EnvDefault, A]): User = request.identity
  implicit def userAwareRequest2UserOpt[A](implicit request: UserAwareRequest[EnvDefault, A]): Option[User] = request.identity
}
