package api

import cats.effect.IO
import domain.scenario.{ScenarioElement, ScenarioGraph, ScenarioValidationResult, ScenarioValidator}
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
        upcastErrorsUnsafe(ScenarioValidator.validate(graph))
      )
    )

  private def upcastErrorsUnsafe(
      validationResult: ScenarioValidationResult
  ): ApiScenarioValidationResult = {
    ApiScenarioValidationResult(
      (validationResult.graphErrors ++ validationResult.scenarioErrors).map(e =>
        ApiScenarioValidationError(
          e.errorCode.asString,
          e.message,
          e.causes.map(el => el.asInstanceOf[ScenarioElement])
        )
      )
    )
  }
}

case class ApiScenarioValidationResult(
    errors: Seq[ApiScenarioValidationError]
)

case class ApiScenarioValidationError(errorCode: String, message: String, causes: Seq[ScenarioElement])
