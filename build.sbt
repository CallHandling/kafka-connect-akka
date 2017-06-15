import sbt.Keys.resolvers

lazy val akkaVersion = "2.4.16"

lazy val commonSettings = Seq(
  name := "kafka-connect-akka",
  organization := "callhandling.co.uk",
  version := "0.1.0",
  scalaVersion := "2.12.1",
  scalacOptions := Seq(
    "-deprecation",
    "-encoding", "UTF-8",
    "-feature",
    "-target:jvm-1.8",
    "-unchecked",
    "-Ywarn-adapted-args",
    "-Ywarn-dead-code",
    "-Ywarn-numeric-widen",
    "-Ywarn-unused",
    "-Ywarn-value-discard",
    "-Xfuture",
    "-Xlint"
  ),
  description := "A Kafka-Connect-Akka Sink connector.",
  resolvers ++= Seq(
    Resolver.bintrayRepo("scalaz", "releases"),
    "Akka Snapshot Repository" at "http://repo.akka.io/snapshots/",
    "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/releases",
    "krasserm at bintray" at "http://dl.bintray.com/krasserm/maven",
    "logger" at "https://mvnrepository.com/artifact"))

lazy val root = (project in file("."))
  .settings(commonSettings,
    libraryDependencies ++= Seq(
      "org.apache.kafka" % "connect-api" % "0.10.2.0",
      "joda-time" % "joda-time" % "2.9.3",
      "org.joda" % "joda-convert" % "1.8.1",
      "org.apache.kafka" % "connect-runtime" % "0.10.2.0",
      "com.typesafe.akka" %% "akka-cluster" % akkaVersion,
      "com.typesafe.akka" %% "akka-cluster-tools" % akkaVersion,
      "com.typesafe.akka" %% "akka-actor" % akkaVersion
    )
  )

//sbt assembly
assemblyMergeStrategy in assembly := {
  case PathList("META-INF", "io.netty.versions.properties") => MergeStrategy.last
  case x =>
    val oldStrategy = (assemblyMergeStrategy in assembly).value
    oldStrategy(x)
}
