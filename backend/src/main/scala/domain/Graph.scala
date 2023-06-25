package domain

import java.util.UUID

final case class Graph(nodes: Seq[Node], relations: Seq[Relation])

sealed trait Element {
  val id: UUID
}

final case class Node(id: UUID) extends Element

final case class Relation(id: UUID, source: Node, target: Node) extends Element
