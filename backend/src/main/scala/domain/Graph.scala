package domain

import java.util.UUID

object Graph {
  type GraphId = Long
  type ElementId = UUID
}

sealed trait ElementContent

final case class Properties() extends ElementContent
final case class Conditions() extends ElementContent

object Properties {
  val empty: Properties = Properties()
}

sealed trait Graph[C <: ElementContent] {
  val id: Graph.GraphId
  val nodes: Seq[Node[C]]
  val relations: Seq[Relation[C]]
}

sealed trait Element[C <: ElementContent] {
  val id: Graph.ElementId
  val content: C
}

final case class Node[C <: ElementContent](id: Graph.ElementId, content: C) extends Element[C]

final case class Relation[C <: ElementContent](id: Graph.ElementId, source: Node[C], target: Node[C], content: C)
    extends Element[C]

final case class ScenarioGraph(id: Graph.GraphId, nodes: Seq[Node[Properties]], relations: Seq[Relation[Properties]])
    extends Graph[Properties]

final case class RuleGraph(id: Graph.GraphId, nodes: Seq[Node[Conditions]], relations: Seq[Relation[Conditions]])
    extends Graph[Conditions]
