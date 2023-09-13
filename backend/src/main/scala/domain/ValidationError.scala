package domain

sealed trait ValidationError {
  val message: String
  val causes: Seq[Element]
}

case class ElementIdNotUnique(elements: Seq[Element]) extends ValidationError {
  override val message: String = s"Element id has to be unique. Non unique id: ${elements.head.id}."
  override val causes: Seq[Element] = elements
}

case class RelationNotUnique(relations: Seq[Relation]) extends ValidationError {
  override val message: String =
    s"""Relation between source and target node has to be unique.
       |Relations with the same source and target nodes ids: ${relations.map(_.id)}.
       |Source node id: ${relations.head.source}
       |Target node id: ${relations.head.target}""".stripMargin
  override val causes: Seq[Element] = relations
}

case class MissingNodeError(node: Node) extends ValidationError {
  override val message: String = s"Node referred to by a relation is missing. Node id: ${node.id}"
  override val causes: Seq[Element] = Seq(node)
}
