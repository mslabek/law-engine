package domain

import java.util.UUID

sealed trait ValidationError {
  def message: String
}

case class ElementIdsNotUnique(id: Seq[UUID]) extends ValidationError {

  override def message: String = s"Element id's have to be unique. Non unique ids: $id."

}
