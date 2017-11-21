package forms

import play.api.data.Form
import play.api.data.Forms._

object FormSignUp {
  val form = Form(
    mapping(
      "username" -> nonEmptyText(minLength = 4, maxLength = 64),
      "email" -> nonEmptyText,
      "password" -> nonEmptyText(minLength = 20),
      "confirm" -> nonEmptyText(minLength = 20)
    )(Data.apply)(Data.unapply)
  )

  /**
    * The forms data case class.
    *
    * @param username The unique username or email used for the login.
    * @param email    The mail address used to contact the user.
    * @param password The password used to login to the account.
    * @param confirm  The password repeated to circumvent user errors.
    */
  case class Data(
    username: String,
    email: String,
    password: String,
    confirm: String
  )

}
