package domain.rule

import domain.graph.{ElementContent, Graph, Node, Relation}

final case class RuleGraph(id: Graph.GraphId, nodes: Seq[Node[Conditions]], relations: Seq[Relation[Conditions]])
    extends Graph[Conditions]

final case class Conditions() extends ElementContent
