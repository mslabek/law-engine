package domain

import cats.data._
import cats.syntax.all._

object GraphValidator {

  private type ValidationResult[A] = ValidatedNec[ValidationError, A]

  private def nonUnique[A](ids: Seq[A]): Seq[A] = {
    ids
      .groupBy(identity)
      .filter { case (_, occurrences) => occurrences.length > 1 }
      .keys
      .toSeq
  }

  private def validateUniqueIds(graph: Graph): ValidationResult[Graph] = {
    val allIds = graph.nodes.map(_.id) ++ graph.relations.map(_.id)
    val idsUnique = allIds.distinct.size == allIds.size

    if (idsUnique) graph.validNec
    else ElementIdsNotUnique(nonUnique(allIds)).invalidNec
  }

  def validate(graph: Graph): ValidationResult[Graph] = {
    validateUniqueIds(graph)
  }

}
