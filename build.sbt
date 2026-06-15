name := "play-rmq"

version := "1.0.0"

scalaVersion := "3.8.4"

libraryDependencies ++= Seq(
  guice,
  "com.rabbitmq" % "amqp-client" % "5.31.0"
)

lazy val root = (project in file(".")).enablePlugins(PlayJava)