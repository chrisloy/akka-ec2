import sbt._
import sbt.{Build => SbtBuild}
import Keys._

object Build extends SbtBuild {

  lazy val project = Project (
    "akka-ec2",
    file("."),
    settings =
      Defaults.defaultSettings ++
      Seq(
        organization := "net.chrisloy",
        name := "akka-ec2",
        version := "0.1",
        scalaVersion := "2.10.3",
        resolvers += Classpaths.typesafeReleases,
        libraryDependencies ++= Seq(
          "com.typesafe.akka" %% "akka-actor" % "2.3.2",
          "com.typesafe.akka" %% "akka-cluster" % "2.3.2",
          "com.amazonaws" % "aws-java-sdk" % "1.7.8",
          "ch.qos.logback" % "logback-classic" % "1.0.6" % "runtime"
        )
    )
  )
}
