
name := "Unison"
version := "1.0"

lazy val `unison` = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.8"

unmanagedResourceDirectories in Test <+= baseDirectory(_ / "target/web/public/test")

resolvers ++= Seq(
  "Scalaz Bintray Repo"    at "https://dl.bintray.com/scalaz/releases",
  "Atlassian Releases"     at "https://maven.atlassian.com/public/"
)

//TwirlKeys.constructorAnnotations += "@javax.inject.Inject()"

//Playframework default dependencies
libraryDependencies ++= Seq(
  cache,
  ws,
  specs2 % Test,
  evolutions,
  "org.scalatestplus.play" %% "scalatestplus-play" % "1.5.1" % Test
)

//Miscellaneous dependencies
libraryDependencies ++= Seq(
  "net.codingwell" %% "scala-guice" % "4.1.0",
  "com.iheart"     %% "ficus"       % "1.4.1"
)

//Database dependencies
libraryDependencies ++= Seq(
  "com.typesafe.play" %% "play-slick"            % "2.0.2",
  "com.typesafe.play" %% "play-slick-evolutions" % "2.0.2",
  "com.h2database"    % "h2"                     % "1.3.176"
)

//Security Dependencies
libraryDependencies ++= Seq(
  "com.mohiva" %% "play-silhouette"                 % "4.0.0",
  "com.mohiva" %% "play-silhouette-password-bcrypt" % "4.0.0",
  "com.mohiva" %% "play-silhouette-crypto-jca"      % "4.0.0",
  "com.mohiva" %% "play-silhouette-persistence"     % "4.0.0",
  "com.mohiva" %% "play-silhouette-testkit"         % "4.0.0" % "test"
)

//Webjars
libraryDependencies ++= Seq(
  "org.webjars"       %% "webjars-play"  % "2.5.0",
  "org.webjars.bower" % "bootstrap-sass" % "3.3.7"
)