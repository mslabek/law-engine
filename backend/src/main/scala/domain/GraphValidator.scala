package domain

import cats.data.Validated._
import cats.data._
import cats.syntax.all._
import domain.ValidatorUtils.duplicates

object GraphValidator {

  private def validateUniqueIds[C <: ElementContent](graph: Graph[C]): Validated[Seq[ValidationError[C]], Unit] = {
    duplicates(graph.nodes ++ graph.relations, (a: Element[C]) => a.id) match {
      case d if d.isEmpty => valid()
      case d              => invalid(d.map(ElementIdNotUnique[C]))
    }
  }

  private def validateUniqueRelations[C <: ElementContent](
      graph: Graph[C]
  ): Validated[Seq[ValidationError[C]], Unit] = {
    duplicates(graph.relations, (relation: Relation[C]) => (relation.source, relation.target)) match {
      case d if d.isEmpty => valid()
      case d              => invalid(d.map(RelationNotUnique[C]))
    }
  }

  private def validateNodesPresent[C <: ElementContent](graph: Graph[C]): Validated[Seq[ValidationError[C]], Unit] = {
    graph.relations.flatMap(a => Seq(a.target, a.source)).toSet.diff(graph.nodes.toSet) match {
      case m if m.isEmpty => valid()
      case m              => invalid(m.map(MissingNodeError[C]).toSeq)
    }
  }

  // TODO: Add validation for disallowing self-related nodes
  def validate[C <: ElementContent](graph: Graph[C]): Validated[Seq[ValidationError[C]], Graph[C]] = {
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
