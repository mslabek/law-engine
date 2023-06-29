package api

import cats.Monad
import io.circe.syntax._
import org.http4s.HttpRoutes
import org.http4s.circe._
import org.http4s.dsl.Http4sDsl

object DemoRoutes {

  def helloWorldRoutes[F[_]: Monad]: HttpRoutes[F] = {
    val dsl = Http4sDsl[F]
    import dsl._
    HttpRoutes
      .of[F] { case GET -> Root / "hello" =>
        Ok("Hello".asJson)
      }
  }

}
