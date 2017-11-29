package controllers

import javax.inject._

import org.webjars.play.WebJarsUtil
import play.api.i18n.I18nSupport
import play.api.mvc._


@Singleton
class ControllerApplication @Inject()
(
  cc: ControllerComponents
)(
  implicit
  webJarsUtil: WebJarsUtil,
  assets: AssetsFinder
) extends InjectedController with I18nSupport {
  def view = Action { implicit request =>
    Ok(views.html.landing())
  }
}