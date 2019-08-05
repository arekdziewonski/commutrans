val ScalatraVersion = "2.6.5"

organization := "com.github.arekdziewonski"

name := "CommuTrans"

version := "0.1.0-SNAPSHOT"

scalaVersion := "2.12.6"

resolvers += Classpaths.typesafeReleases

libraryDependencies ++= Seq(
  "org.scalatra" %% "scalatra" % ScalatraVersion,
  "org.scalatra" %% "scalatra-scalatest" % ScalatraVersion % "test",
  "ch.qos.logback" % "logback-classic" % "1.2.3" % "runtime",
  "org.eclipse.jetty" % "jetty-webapp" % "9.4.9.v20180320" % "container",
  "javax.servlet" % "javax.servlet-api" % "3.1.0" % "provided",
  "org.webjars" % "bootstrap" % "4.3.1",
  "org.webjars" % "jquery" % "3.4.1"
)

enablePlugins(SbtTwirl)
enablePlugins(ScalatraPlugin)
