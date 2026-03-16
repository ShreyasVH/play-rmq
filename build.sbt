name := "play-rmq"

version := "1.0.0"

scalaVersion := "3.8.2"

libraryDependencies ++= Seq(
  guice,
  "com.rabbitmq" % "amqp-client" % "5.29.0"
)

lazy val root = (project in file(".")).enablePlugins(PlayJava)