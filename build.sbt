name := "play-http-metrics"
organization := "thind.io"
version := "1.0"
scalaVersion := "2.12.8"


resolvers ++= Seq(
  "snapshots" at "https://scala-tools.org/repo-snapshots",
  "releases"  at "https://scala-tools.org/repo-releases")

resolvers ++= Seq(
  "sonatype-snapshots" at "https://oss.sonatype.org/content/repositories/snapshots",
  "sonatype-releases"  at "https://oss.sonatype.org/content/repositories/releases")

libraryDependencies ++= {
  Seq(
    "org.scala-lang" % "scala-library" % "2.12.8",
    "com.typesafe.play" %% "play-ws" % "2.6.9",
    "io.dropwizard.metrics" % "metrics-core" % "3.1.2",
    "io.dropwizard.metrics" % "metrics-json" % "3.1.2",
    "io.dropwizard.metrics" % "metrics-jvm" % "3.1.2",
    "org.specs2" %% "specs2-core" % "4.10.5" % Test,
    "org.specs2" %% "specs2-mock" % "4.10.5" % Test
  )
}