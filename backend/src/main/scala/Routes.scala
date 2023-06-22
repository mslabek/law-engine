import cats.data.Kleisli
import cats.effect.IO
import org.http4s.Method.GET
import org.http4s.dsl.io._
import org.http4s.{HttpRoutes, Request, Response}
import org.http4s.circe._
import io.circe.syntax._
import io.circe.generic.auto._

object Routes {

  private val helloWorldObj: HelloWorldClass = HelloWorldClass("Hello world!")

  val helloService: Kleisli[IO, Request[IO], Response[IO]] = HttpRoutes
    .of[IO] { case GET -> Root / "hello" =>
      BadGateway(helloWorldObj.asJson)
    }
    .orNotFound

}

case class HelloWorldClass(message: String)
