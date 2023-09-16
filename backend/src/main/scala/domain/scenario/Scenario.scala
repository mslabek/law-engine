package domain.scenario

import domain.graph.{ElementContent, Graph, Node, Relation}

final case class ScenarioGraph(id: Graph.GraphId, nodes: Seq[Node[Properties]], relations: Seq[Relation[Properties]])
    extends Graph[Properties]

final case class Properties() extends ElementContent

object Properties {
  val empty: Properties = Properties()
}
