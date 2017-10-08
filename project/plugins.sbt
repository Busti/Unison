logLevel := Level.Warn

resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"
resolvers += "Sassify bintray"     at "http://dl.bintray.com/irundaia/sbt-plugins"

addSbtPlugin("com.typesafe.play" % "sbt-plugin"  % "2.6.6")
addSbtPlugin("org.irundaia.sbt"  % "sbt-sassify" % "1.4.8")
//addSbtPlugin("com.typesafe.sbt"  % "sbt-twirl"   % "1.2.0")