package domain.graph

import domain.{ErrorCode, ValidationError}

case class ElementIdNotUnique(elements: Seq[Element]) extends ValidationError[Element] {
  override val errorCode: ErrorCode = ErrorCode.ElementIdNotUnique
  override val message: String = s"Element id has to be unique. Non unique id: ${elements.head.id}."
  override val causes: Seq[Element] = elements
}

case class RelationNotUnique(relations: Seq[Relation]) extends ValidationError[Element] {
  override val errorCode: ErrorCode = ErrorCode.RelationNotUnique
  override val message: String =
    s"""Relation between source and target node has to be unique.
       |Relations with the same source and target nodes ids: ${relations.map(_.id)}.
       |Source node id: ${relations.head.source}
       |Target node id: ${relations.head.target}""".stripMargin
  override val causes: Seq[Element] = relations
}

case class NodeMissing(node: Node) extends ValidationError[Element] {
  override val errorCode: ErrorCode = ErrorCode.MissingNode
  override val message: String = s"Node referred to by a relation is missing. Node id: ${node.id}"
  override val causes: Seq[Element] = Seq(node)
}
