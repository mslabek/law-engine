package api

import cats.effect.IO
import domain._
import io.circe.generic.auto._
import sttp.tapir._
import sttp.tapir.generic.auto._
import sttp.tapir.json.circe._
import sttp.tapir.server.ServerEndpoint.Full

object ValidationApi {

  private val scenarioValidationEndpoint: PublicEndpoint[ScenarioGraph, Unit, ApiScenarioValidationResult, Any] =
    endpoint.post
      .in("scenario" / "validate")
      .in(jsonBody[ScenarioGraph])
      .out(jsonBody[ApiScenarioValidationResult])

  val validationServerEndpoint: Full[Unit, Unit, ScenarioGraph, Unit, ApiScenarioValidationResult, Any, IO] =
    scenarioValidationEndpoint.serverLogicSuccess(graph =>
      IO.pure(
        ApiScenarioValidationResult(errors =
          GraphValidator
            .validate(graph)
            .fold(identity, _ => Seq.empty)
            .map(a => ApiScenarioValidationError(a.message, a.causes))
        )
      )
    )
}

case class ApiScenarioValidationResult(errors: Seq[ApiScenarioValidationError])

case class ApiScenarioValidationError(message: String, elements: Seq[Element[Properties]])

case class ApiRulesValidationResult(errors: Seq[ApiRulesValidationError])

case class ApiRulesValidationError(message: String, elements: Seq[Element[Conditions]])
