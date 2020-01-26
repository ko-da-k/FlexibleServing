package controllers

import actors.HelloActor.SayHello
import akka.actor.Status.Success
import akka.actor.{ActorRef, ActorSystem}
import akka.pattern.AskableActorRef
import akka.util.Timeout
import javax.inject.{Inject, Named, Singleton}
import play.api.mvc.{AbstractController, Action, AnyContent, ControllerComponents, Request}

import scala.concurrent.{Await, ExecutionContext, Future}
import scala.concurrent.duration._

@Singleton
class HelloController @Inject()
(@Named("configured-actor") helloActor: AskableActorRef)
(actorSystem: ActorSystem, cc: ControllerComponents)
(implicit exec: ExecutionContext)
  extends AbstractController(cc) {
  def index(): Action[AnyContent] = Action {
    implicit request: Request[AnyContent] =>
      Ok(s"hello!!!!!, $request")
  }

  def get(name: Option[String]): Action[AnyContent] = Action.async {
    implicit request: Request[AnyContent] => {
      implicit val timeout: Timeout = Timeout(2.second)
      val f: Future[Any] = helloActor ? SayHello(name)
      Await.ready(f, 0.second)
      f.mapTo[String].map {
        msg => Ok(msg)
      }
    }
  }
}
