package controllers.auth

import javax.inject.Inject

import com.mohiva.play.silhouette.api.Silhouette
import play.api.i18n.{I18nSupport, MessagesApi}
import play.api.mvc._
import utils.auth.EnvSession

class UserAuth @Inject()(
    val messagesApi: MessagesApi,
    silhouette: Silhouette[EnvSession]
  ) extends Controller with I18nSupport {



  def signUp = silhouette.UnsecuredAction { implicit request =>
    Redirect(request.path)
  }
}
