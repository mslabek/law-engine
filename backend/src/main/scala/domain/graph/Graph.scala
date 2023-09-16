package domain.graph

import java.util.UUID

object Graph {
  type GraphId = Long
  type ElementId = UUID
}

trait ElementContent

trait Graph[C <: ElementContent] {
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

