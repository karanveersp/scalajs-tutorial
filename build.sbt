import Dependencies._

enablePlugins(ScalaJSPlugin)

ThisBuild / scalaVersion     := "2.13.2"
ThisBuild / version          := "0.1.0-SNAPSHOT"

// application with a main method
scalaJSUseMainModuleInitializer := true

lazy val root = (project in file("."))
  .settings(
    name := "scalajs-tutorial",
    libraryDependencies += scalaTest % Test,
    libraryDependencies += "org.scala-js" %%% "scalajs-dom" % "1.0.0"
  )