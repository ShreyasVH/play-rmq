name := "play-rmq"

version := "1.0.0"

scalaVersion := "2.13.0"

libraryDependencies ++= Seq(
  guice,
  "com.rabbitmq" % "amqp-client" % "5.8.0"
)

lazy val root = (project in file(".")).enablePlugins(PlayJava)