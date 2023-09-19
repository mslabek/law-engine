package domain.scenario

import cats.data.Validated
import domain.graph.{Element, GraphValidator}
import domain.{ErrorCode, ValidationError}

object ScenarioValidator {

  def validate(graph: ScenarioGraph): ScenarioValidationResult = {
    GraphValidator.validate(graph) match {
      case Validated.Valid(_)   => ScenarioValidationResult(Seq.empty, Seq.empty)
      case Validated.Invalid(e) => ScenarioValidationResult(e, Seq.empty)
    }
  }

}

trait ScenarioValidationError extends ValidationError[ScenarioElement] {
  val errorCode: ErrorCode
  val message: String
  val causes: Seq[ScenarioElement]
}

final case class ScenarioValidationResult(
    graphErrors: Seq[ValidationError[Element]],
    scenarioErrors: Seq[ScenarioValidationError]
)
