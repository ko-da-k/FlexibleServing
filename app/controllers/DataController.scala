package controllers

import akka.actor.ActorSystem
import akka.pattern.AskableActorRef
import javax.inject.{Inject, Named}
import play.api.mvc._

import scala.concurrent.ExecutionContext

class DataController @Inject()
(@Named("configured-actor") dataActor: AskableActorRef)
(cc: ControllerComponents, system: ActorSystem)
(implicit exec: ExecutionContext)
  extends AbstractController(cc) {

  def find: Action[AnyContent] = Action {
    implicit request: Request[AnyContent] =>
      request.body.asJson match {
        case Some(x) => Ok(x).as("application/json")
        case _ => BadRequest("invalid json body").as("application/json")
      }
  }

  def register: Action[AnyContent] = Action {
    implicit request: Request[AnyContent] =>
      Ok(request.body.toString)
  }

  def remove: Action[AnyContent] = Action {
    implicit request: Request[AnyContent] =>
      Ok(request.body.toString)
  }
}
