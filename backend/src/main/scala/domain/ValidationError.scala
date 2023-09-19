package domain

trait ValidationError[T] {
  val errorCode: ErrorCode
  val message: String
  val causes: Seq[T]
}

sealed trait ErrorCode {
  val asString: String
}

object ErrorCode {
  case object ElementIdNotUnique extends ErrorCode {
    override val asString: String = "ELEMENT_ID_NOT_UNIQUE"
  }
  case object RelationNotUnique extends ErrorCode {
    override val asString: String = "RELATION_NOT_UNIQUE"
  }
  case object MissingNode extends ErrorCode {
    override val asString: String = "NODE_MISSING"
  }
}
