package domain.graph

sealed trait ValidationError[C <: ElementContent] {
  val message: String
  val causes: Seq[Element[C]]
}

case class ElementIdNotUnique[C <: ElementContent](elements: Seq[Element[C]]) extends ValidationError[C] {
  override val message: String = s"Element id has to be unique. Non unique id: ${elements.head.id}."
  override val causes: Seq[Element[C]] = elements
}

case class RelationNotUnique[C <: ElementContent](relations: Seq[Relation[C]]) extends ValidationError[C] {
  override val message: String =
    s"""Relation between source and target node has to be unique.
       |Relations with the same source and target nodes ids: ${relations.map(_.id)}.
       |Source node id: ${relations.head.source}
       |Target node id: ${relations.head.target}""".stripMargin
  override val causes: Seq[Element[C]] = relations
}

case class MissingNodeError[C <: ElementContent](node: Node[C]) extends ValidationError[C] {
  override val message: String = s"Node referred to by a relation is missing. Node id: ${node.id}"
  override val causes: Seq[Element[C]] = Seq(node)
}
