package forms

import play.api.data.Form
import play.api.data.Forms._

object FormSignUp {
  val form = Form(
    mapping(
      "username" -> nonEmptyText(minLength = 4, maxLength = 64),
      "email" -> nonEmptyText,
      "password" -> nonEmptyText(minLength = 8),
      "confirm" -> nonEmptyText(minLength = 8)
    )(Data.apply)(Data.unapply)
  )

  /**
    * The forms data object.
    *
    * @param username The unique username or email used for the login.
    * @param password
    */
  case class Data(
    username: String,
    email: String,
    password: String,
    confirm: String
  )

}
