name := "play-rmq"

version := "1.0.0"

scalaVersion := "3.10.1"

libraryDependencies ++= Seq(
  guice,
  "com.rabbitmq" % "amqp-client" % "5.35.0"
)

lazy val root = (project in file(".")).enablePlugins(PlayJava)