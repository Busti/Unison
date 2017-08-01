package controllers

import javax.inject.Inject

import com.mohiva.play.silhouette.api.Silhouette
import com.mohiva.play.silhouette.api.actions.SecuredRequest
import controllers.auth.ControllerAuth
import play.api.i18n.MessagesApi
import play.api.mvc.AnyContent
import utils.silhouette.EnvDefault

import scala.concurrent.Future

class ControllerApplication @Inject()(
  val silhouette: Silhouette[EnvDefault],
  webJarAssets: ReverseWebJarAssets,
  val messagesApi: MessagesApi)
  extends ControllerAuth {

  def index = silhouette.SecuredAction.async { implicit request: SecuredRequest[EnvDefault, AnyContent] =>
    Future.successful(Ok(views.html.index(webJarAssets)))
  }
}