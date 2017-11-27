package controllers

import javax.inject._
import play.api.i18n.{I18nSupport, MessagesApi}
import play.api.mvc._

@Singleton
class ControllerApplication @Inject()(
  cc: ControllerComponents,
  override implicit val messagesApi: MessagesApi)
  extends InjectedController with I18nSupport {

  def index = Action {
      Ok("Test")//()views.html.index
  }
}