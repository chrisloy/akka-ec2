package net.chrisloy.akka

import akka.actor.{Props, ActorSystem}
import scala.concurrent.duration._
import scala.util.Random

object Main extends App {

  val system = ActorSystem("akka-ec2", AkkaConfig.config)
  val broadcaster = system.actorOf(Props[BroadcastActor], name = "broadcast")

  println("Started up")

  implicit val executor = system.dispatcher
  system.scheduler.schedule(0 seconds, 5 seconds) {
    val words = Random.shuffle(
      List("peter", "piper", "picked", "a", "peck", "of", "pickled", "pepper")
    )
    broadcaster ! Message(words mkString " ")
  }
}
