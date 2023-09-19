package domain.graph

import java.util.UUID

object Graph {
  type GraphId = Long
  type ElementId = UUID
}

trait Graph {
  val id: Graph.GraphId
  val nodes: Seq[Node]
  val relations: Seq[Relation]
}

sealed trait Element {
  val id: Graph.ElementId
}

trait Node extends Element

trait Relation extends Element {
  val source: Node
  val target: Node
}
