package api

import cats._
import domain.{Graph, Node, Relation}
import org.http4s.circe._
import io.circe.syntax._
import io.circe.generic.auto._
import org.http4s.HttpRoutes
import org.http4s.dsl.Http4sDsl

import java.util.UUID

object GraphRoutes {

  def graphRoutes[F[_]: Monad]: HttpRoutes[F] = {
    val dsl = Http4sDsl[F]
    import dsl._
    HttpRoutes.of[F] {
      case GET -> Root / "graph" => Ok(stubGraph.asJson)
    }
  }

  private val stubGraph: Graph = {
    val testUUID1 = UUID.fromString("00000000-0000-0000-0000-000000000001")
    val testUUID2 = UUID.fromString("00000000-0000-0000-0000-000000000002")
    val testUUID3 = UUID.fromString("00000000-0000-0000-0000-000000000003")
    val node1 = Node(testUUID1)
    val node2 = Node(testUUID2)
    val relation = Relation(testUUID3, node1, node2)
    Graph(1, Seq(node1, node2), Seq(relation))
  }

}
