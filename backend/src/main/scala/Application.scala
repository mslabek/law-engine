import akka.actor.typed.ActorSystem
import akka.actor.typed.javadsl.Behaviors
import akka.http.scaladsl.Http

import scala.concurrent.ExecutionContext

object Application extends App {

  private def runApplication(): Unit = {
    implicit val system: ActorSystem[Nothing] = ActorSystem(Behaviors.empty, "law-engine")
    implicit val executionContext: ExecutionContext = system.executionContext

    initializeServer()
  }

  private def initializeServer()(implicit
      ec: ExecutionContext,
      actorSystem: ActorSystem[Nothing]
  ): Unit = {
    val rootRoute = new RootRoute().route
    Http().newServerAt("localhost", 8080).bind(rootRoute)
  }

  runApplication()
}
