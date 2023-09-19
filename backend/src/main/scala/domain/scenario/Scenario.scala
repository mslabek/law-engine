package domain.scenario

import domain.graph.{Graph, Node, Relation}

final case class ScenarioGraph(id: Graph.GraphId, nodes: Seq[ScenarioNode], relations: Seq[ScenarioRelation])
    extends Graph

sealed trait ScenarioElement

final case class ScenarioNode(id: Graph.ElementId, properties: Properties) extends Node with ScenarioElement

final case class ScenarioRelation(
    id: Graph.ElementId,
    source: ScenarioNode,
    target: ScenarioNode,
    properties: Properties
) extends Relation
    with ScenarioElement

final case class Properties()

object Properties {
  val empty: Properties = Properties()
}
