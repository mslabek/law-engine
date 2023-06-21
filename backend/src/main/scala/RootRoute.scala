import akka.http.scaladsl.model.StatusCodes.OK
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import io.circe.generic.auto._
import io.circe.syntax._
import de.heikoseeberger.akkahttpcirce.FailFastCirceSupport._

class RootRoute {

  private val helloWorldObj: HelloWorldClass = HelloWorldClass("Hello world!")

  private val helloWorldRoute: Route =
    path("hello") {
      get {
        complete {
          OK -> helloWorldObj.asJson
        }
      }
    }

  def route: Route = helloWorldRoute
}

case class HelloWorldClass(message: String)
