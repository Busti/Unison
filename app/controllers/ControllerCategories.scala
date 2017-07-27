package controllers

import javax.inject.Inject

import play.api.i18n.{I18nSupport, MessagesApi}
import play.api.mvc.Controller

class ControllerCategories @Inject()(val messagesApi: MessagesApi) extends Controller with I18nSupport {
  def overview = TODO
}
