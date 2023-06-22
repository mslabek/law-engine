ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.11"

val circeVersion = "0.14.5"
val http4sVersion = "0.23.21"

lazy val root = (project in file("."))
  .settings(name := "root")

lazy val backend = (project in file("backend"))
  .settings(
    name := "backend",
    libraryDependencies ++= Seq(
      "io.circe" %% "circe-core" % circeVersion,
      "io.circe" %% "circe-generic" % circeVersion,
      "io.circe" %% "circe-parser" % circeVersion,
      "org.http4s" %% "http4s-ember-client" % http4sVersion,
      "org.http4s" %% "http4s-ember-server" % http4sVersion,
      "org.http4s" %% "http4s-dsl" % http4sVersion,
      "org.http4s" %% "http4s-circe" % http4sVersion
    )
  )

lazy val frontend = (project in file("frontend"))
  .settings(name := "frontend")
