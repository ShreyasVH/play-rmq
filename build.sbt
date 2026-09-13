name := "play-rmq"

version := "1.0.0"

scalaVersion := "3.9.0"

libraryDependencies ++= Seq(
  guice,
  "com.rabbitmq" % "amqp-client" % "5.35.0"
)

lazy val root = (project in file(".")).enablePlugins(PlayJava)