import api.GraphEndpoints
import cats.effect._
import com.comcast.ip4s.{Host, Port}
import org.http4s.ember.server.EmberServerBuilder
import org.http4s.server.Router
import sttp.tapir.server.http4s.Http4sServerInterpreter

object Application extends IOApp {

  override def run(args: List[String]): IO[ExitCode] = {

    val routes = Http4sServerInterpreter[IO]().toRoutes(GraphEndpoints.helloServerEndpoint)

    val port = Port.fromInt(8080).get

    EmberServerBuilder
      .default[IO]
      .withHost(Host.fromString("localhost").get)
      .withPort(port)
      .withHttpApp(Router("/" -> routes).orNotFound)
      .build
      .use(_ => IO.never)
      .as(ExitCode.Success)
  }
}
