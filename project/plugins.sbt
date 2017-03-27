logLevel := Level.Warn

resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"
resolvers += "Sassify bintray"     at "http://dl.bintray.com/irundaia/sbt-plugins"

addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.5.10")
addSbtPlugin("org.irundaia.sbt" % "sbt-sassify" % "1.4.8")