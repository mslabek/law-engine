import api.{DemoRoutes, GraphRoutes}
import cats.effect._
import com.comcast.ip4s.IpLiteralSyntax
import org.http4s.ember.server.EmberServerBuilder
import org.http4s.server.Router

object Application extends IOApp {

  override def run(args: List[String]): IO[ExitCode] = {
    EmberServerBuilder
        .default[IO]
        .withHost(ipv4"0.0.0.0")
        .withPort(port"8080")
        .withHttpApp(routes)
        .build
        .use(_ => IO.never)
        .as(ExitCode.Success)
  }

  private val routes = Router(
    "/graph" -> GraphRoutes.graphRoutes[IO],
    "/demo" -> DemoRoutes.helloWorldRoutes[IO]
  ).orNotFound

}
