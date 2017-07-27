package utils.silhouette

import com.mohiva.play.silhouette.api.Env
import com.mohiva.play.silhouette.impl.authenticators.CookieAuthenticator
import models.User

class EnvCookie extends Env {
  type I = User
  type A = CookieAuthenticator
}
