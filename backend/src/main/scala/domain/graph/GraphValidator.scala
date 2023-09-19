package domain.graph

import cats.data.Validated._
import cats.data._
import cats.syntax.all._
import domain.ValidationError
import domain.graph.ValidatorUtils.duplicates

object GraphValidator {

  private def validateUniqueIds(graph: Graph): Validated[Seq[ValidationError[Element]], Unit] = {
    duplicates(graph.nodes ++ graph.relations, (a: Element) => a.id) match {
      case d if d.isEmpty => valid()
      case d              => invalid(d.map(ElementIdNotUnique))
    }
  }

  private def validateUniqueRelations(
      graph: Graph
  ): Validated[Seq[ValidationError[Element]], Unit] = {
    duplicates(graph.relations, (relation: Relation) => (relation.source, relation.target)) match {
      case d if d.isEmpty => valid()
      case d              => invalid(d.map(RelationNotUnique))
    }
  }

  private def validateNodesPresent(graph: Graph): Validated[Seq[ValidationError[Element]], Unit] = {
    graph.relations.flatMap(a => Seq(a.target, a.source)).toSet.diff(graph.nodes.toSet) match {
      case m if m.isEmpty => valid()
      case m              => invalid(m.map(NodeMissing).toSeq)
    }
  }

  // TODO: Add validation for disallowing self-related nodes
  def validate(graph: Graph): Validated[Seq[ValidationError[Element]], Graph] = {
    (
      validateUniqueIds(graph),
      validateNodesPresent(graph),
      validateUniqueRelations(graph)
    ).mapN((_, _, _) => graph)
  }

}

object ValidatorUtils {
  def duplicates[A](elements: Seq[A], groupingCriteria: A => _): Seq[Seq[A]] = {
    elements
      .groupBy(groupingCriteria)
      .filter { case (_, occurrences) => occurrences.length > 1 }
      .values
      .toSeq
  }
}
