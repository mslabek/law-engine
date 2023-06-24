package domain

import java.util.UUID

final case class Graph(nodes: Seq[Node], relations: Seq[Relation])

final case class Node(id: UUID)

final case class Relation(id: UUID)
