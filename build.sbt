ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.11"

lazy val root = (project in file("."))
  .settings(name := "root")

lazy val backend = (project in file("backend"))

lazy val frontend = (project in file("frontend"))
