package utils.auth

import com.mohiva.play.silhouette.api.Env
import com.mohiva.play.silhouette.impl.authenticators.SessionAuthenticator
import models.User

class EnvSession extends Env {
  type I = User
  type A = SessionAuthenticator
}
