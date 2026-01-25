name := "play-rmq"

version := "1.0.0"

scalaVersion := "2.13.18"

libraryDependencies ++= Seq(
  guice,
  "com.rabbitmq" % "amqp-client" % "5.28.0"
)

lazy val root = (project in file(".")).enablePlugins(PlayJava)