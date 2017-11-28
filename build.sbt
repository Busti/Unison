name := "Unison"
version := "1.0"

lazy val `unison` = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.12.3"

unmanagedResourceDirectories in Test += baseDirectory.value / "target/web/public/test"

resolvers ++= Seq(
  "Scalaz Bintray Repo" at "https://dl.bintray.com/scalaz/releases",
  "Atlassian Releases"  at "https://maven.atlassian.com/public/",
  "Sonatype snapshots"  at "https://oss.sonatype.org/content/repositories/snapshots"
)

//TwirlKeys.constructorAnnotations += "@javax.inject.Inject()"

//Playframework default dependencies
libraryDependencies ++= Seq(
  ws,
  specs2 % Test,
  evolutions,
  "org.scalatestplus.play" %% "scalatestplus-play" % "3.1.2" % Test
)

//Miscellaneous dependencies
libraryDependencies ++= Seq(
  "net.codingwell" %% "scala-guice" % "4.1.0",
  guice,
  "com.iheart"     %% "ficus"       % "1.4.1"
)

//Database dependencies
libraryDependencies ++= Seq(
  "com.typesafe.play" %% "play-slick"            % "3.0.2",
  "com.typesafe.play" %% "play-slick-evolutions" % "3.0.2",
  "com.h2database"    %  "h2"                    % "1.4.196"
)

//Security Dependencies
libraryDependencies ++= Seq(
  "com.mohiva" %% "play-silhouette"                 % "5.0.0",
  "com.mohiva" %% "play-silhouette-password-bcrypt" % "5.0.0",
  "com.mohiva" %% "play-silhouette-crypto-jca"      % "5.0.0",
  "com.mohiva" %% "play-silhouette-persistence"     % "5.0.0",
  "com.mohiva" %% "play-silhouette-testkit"         % "5.0.0" % "test"
)

//Webjars
libraryDependencies ++= Seq(
  "org.webjars" %% "webjars-play"   % "2.6.1",
  "org.webjars" %  "bootstrap-sass" % "3.3.7"
)