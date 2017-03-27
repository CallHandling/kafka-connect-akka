import sbt.Keys.resolvers

organization  := "callhandling.co.uk"

version       := "0.1.0"

scalaVersion  := "2.12.1"

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")

description := "A Kafka-Connect-Akka Sink connector."



resolvers ++= Seq(
Resolver.bintrayRepo("scalaz", "releases")
)


scalacOptions ++= Seq(
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
)


resolvers += "Akka Snapshot Repository" at "http://repo.akka.io/snapshots/"

resolvers += "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/releases"
resolvers += "krasserm at bintray" at "http://dl.bintray.com/krasserm/maven"
resolvers += "logger" at "https://mvnrepository.com/artifact"




libraryDependencies ++= Seq(
  "org.apache.kafka"       % "connect-api"            % "0.10.2.0",
  "joda-time"              %  "joda-time"             % "2.9.3",
  "org.joda"               %  "joda-convert"          % "1.8.1",
  "org.apache.kafka"       % "connect-runtime"        % "0.10.2.0",
  "com.typesafe.akka"      %%  "akka-actor"           % "2.4.16"
)

//sbt assembly
assemblyMergeStrategy in assembly := {
  case PathList("META-INF", "io.netty.versions.properties") => MergeStrategy.last
  case x =>
    val oldStrategy = (assemblyMergeStrategy in assembly).value
    oldStrategy(x)
}
