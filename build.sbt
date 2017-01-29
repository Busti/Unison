name := "Unison"

version := "1.0"

lazy val `unison` = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.7"

unmanagedResourceDirectories in Test <+= baseDirectory(_ / "target/web/public/test")

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"

/***************
* Dependencies *
***************/

//Playframework default dependencies
libraryDependencies ++= Seq(
  jdbc,
  cache,
  ws,
  specs2 % Test,
  evolutions
)

//Database dependencies
libraryDependencies ++= Seq(
  "com.typesafe.play" %% "play-slick" % "2.0.2",
  "com.typesafe.play" %% "play-slick-evolutions" % "2.0.2",
  "com.h2database" % "h2" % "1.3.176"
)

//Webjars
libraryDependencies ++= Seq(
  "org.webjars" %% "webjars-play" % "2.5.0",
  "org.webjars" %  "bootstrap"    % "3.3.4",
  "org.webjars" %  "jquery"       % "3.1.1-1"
)