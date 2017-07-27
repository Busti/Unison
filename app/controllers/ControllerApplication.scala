package controllers

import javax.inject.Inject

import com.mohiva.play.silhouette.api.Silhouette
import controllers.auth.ControllerAuth
import play.api.i18n.MessagesApi
import utils.silhouette.EnvCookie

class ControllerApplication @Inject()(
  val silhouette: Silhouette[EnvCookie],
  webJarAssets: WebJarAssets,
  val messagesApi: MessagesApi)
  extends ControllerAuth {

  def index = UserAwareAction { implicit request =>
    Ok(views.html.index(webJarAssets))
  }
}