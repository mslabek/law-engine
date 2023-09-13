package api

import cats.effect.IO
import domain.{Graph, Node, Relation}
import sttp.tapir._
import sttp.tapir.json.circe._
import sttp.tapir.generic.auto._
import sttp.tapir.server.ServerEndpoint
import io.circe.generic.auto._

import java.util.UUID

object GraphEndpoints {

  private val stubGraphEndpoint: PublicEndpoint[Unit, Unit, Graph, Any] = endpoint.get
    .in("hello")
    .out(jsonBody[Graph])

  val helloServerEndpoint: ServerEndpoint[Any, IO] = stubGraphEndpoint.serverLogicSuccess(_ => IO.pure(stubGraph))

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
