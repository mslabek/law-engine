package domain.rule

import domain.graph.{Graph, Node, Relation}

final case class RuleGraph(id: Graph.GraphId, nodes: Seq[Node], relations: Seq[Relation]) extends Graph

final case class Conditions()
