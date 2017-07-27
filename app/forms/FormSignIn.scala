package forms

import play.api.data.Form
import play.api.data.Forms._

object FormSignIn {
  val form = Form(
    mapping(
      "username" -> nonEmptyText,
      "password" -> nonEmptyText
    )(Data.apply)(Data.unapply)
  )

  /**
    * The forms data object.
    * @param username The unique username or email used for the login.
    * @param password
    */
  case class Data(
    username: String,
    password: String
  )
}
