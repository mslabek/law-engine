package domain

import domain.Graph.{ElementId, GraphId}

import java.util.UUID

object Graph {
  type GraphId = Long
  type ElementId = UUID
}

final case class Graph(id: GraphId, nodes: Seq[Node], relations: Seq[Relation])

sealed trait Element {
  val id: ElementId
}

final case class Node(id: ElementId) extends Element

final case class Relation(id: ElementId, source: Node, target: Node) extends Element
