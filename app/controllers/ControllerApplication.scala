package controllers

import javax.inject.Inject

import play.api.i18n.{I18nSupport, MessagesApi}
import play.api.mvc._

class ControllerApplication @Inject()(
  cc: ControllerComponents,
  override implicit val messagesApi: MessagesApi)
  extends AbstractController(cc) with I18nSupport {

  def index = Action {
      Ok(views.html.index)
  }
}