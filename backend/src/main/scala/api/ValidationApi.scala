package api

import cats.effect.IO
import domain._
import io.circe.generic.auto._
import sttp.tapir._
import sttp.tapir.generic.auto._
import sttp.tapir.json.circe._
import sttp.tapir.server.ServerEndpoint.Full

object ValidationApi {

  private val validationEndpoint: PublicEndpoint[Graph, Unit, ValidationResult, Any] = endpoint.post
    .in("graph" / "validate")
    .in(jsonBody[Graph])
    .out(jsonBody[ValidationResult])

  val validationServerEndpoint: Full[Unit, Unit, Graph, Unit, ValidationResult, Any, IO] =
    validationEndpoint.serverLogicSuccess(graph =>
      IO.pure(
        ValidationResult(errors =
          GraphValidator
            .validate(graph)
            .fold(identity, _ => Seq.empty)
            .map(a => ApiGraphValidationError(a.message, a.causes))
        )
      )
    )
}

case class ValidationResult(errors: Seq[ApiGraphValidationError])

case class ApiGraphValidationError(message: String, elements: Seq[Element])
