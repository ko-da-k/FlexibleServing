package actors

import akka.actor.{Actor, Props}
import javax.inject.Inject
import play.api.Configuration

object HelloActor {
  def props = Props[HelloActor]

  case class SayHello(name: Option[String])

}

class HelloActor @Inject()() extends Actor {

  import HelloActor._

  override def receive: Receive = {
    case SayHello(name: Option[String]) =>
      name match {
        case Some(x) => sender ! "Hello, " + x
        case None => sender ! "NoName"
      }
    case _ => sender ! "??????"
  }

  override def postStop: Unit = println("stopped")
}
