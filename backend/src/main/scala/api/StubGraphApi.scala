package api

import cats.effect.IO
import domain.scenario.{Properties, ScenarioGraph, ScenarioNode, ScenarioRelation}
import io.circe.generic.auto._
import sttp.tapir._
import sttp.tapir.generic.auto._
import sttp.tapir.json.circe._
import sttp.tapir.server.ServerEndpoint.Full

import java.util.UUID

object StubGraphApi {

  private val graphEndpoint: PublicEndpoint[Unit, Unit, ScenarioGraph, Any] = endpoint.get
    .in("graph" / "stub")
    .out(jsonBody[ScenarioGraph])

  val graphServerEndpoint: Full[Unit, Unit, Unit, Unit, ScenarioGraph, Any, IO] =
    graphEndpoint.serverLogicSuccess(_ => IO.pure(stubGraph))

  private val stubGraph: ScenarioGraph = {
    val testUUID1 = UUID.fromString("00000000-0000-0000-0000-000000000001")
    val testUUID2 = UUID.fromString("00000000-0000-0000-0000-000000000002")
    val testUUID3 = UUID.fromString("00000000-0000-0000-0000-000000000003")
    val node1 = ScenarioNode(testUUID1, Properties.empty)
    val node2 = ScenarioNode(testUUID2, Properties.empty)
    val relation = ScenarioRelation(testUUID3, node1, node2, Properties.empty)
    ScenarioGraph(1, Seq(node1, node2), Seq(relation))
  }
}
