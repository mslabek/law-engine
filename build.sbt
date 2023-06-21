ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.11"

val AkkaVersion = "2.8.0"
val AkkaHttpVersion = "10.5.2"
val AkkaHttpJsonVersion = "1.39.2"
val circeVersion = "0.14.5"


lazy val root = (project in file("."))
  .settings(name := "root")

lazy val backend = (project in file("backend"))
  .settings(
    name := "backend",
    libraryDependencies ++= Seq(
      "com.typesafe.akka" %% "akka-actor-typed" % AkkaVersion,
      "com.typesafe.akka" %% "akka-stream" % AkkaVersion,
      "com.typesafe.akka" %% "akka-http" % AkkaHttpVersion,
      "io.circe" %% "circe-core" % circeVersion,
      "io.circe" %% "circe-generic" % circeVersion,
      "io.circe" %% "circe-parser" % circeVersion,
      "de.heikoseeberger" %% "akka-http-circe" % AkkaHttpJsonVersion
    )
  )

lazy val frontend = (project in file("frontend"))
  .settings(name := "frontend")
