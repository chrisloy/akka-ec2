package net.chrisloy.akka

import akka.actor.{Props, ActorSystem}

object Main extends App {

  val system = ActorSystem("akka-ec2", AkkaConfig.config)
  val cluster = system.actorOf(Props[BroadcastActor], name = "broadcast")

  println("Started up")

  readLine()
}
