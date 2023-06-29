package domain

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers
import cats.data._
import domain.ValidatorTestUtils.errors

import java.util.UUID

class GraphValidatorTest extends AnyFunSuite with Matchers {

  private val testUUID1 = UUID.fromString("00000000-0000-0000-0000-000000000001")
  private val testUUID2 = UUID.fromString("00000000-0000-0000-0000-000000000002")
  private val testUUID3 = UUID.fromString("00000000-0000-0000-0000-000000000003")
  private val testUUID4 = UUID.fromString("00000000-0000-0000-0000-000000000004")
  private val graphId = 1L

  test("should not return any errors for valid graph") {
    val node1 = Node(testUUID1)
    val node2 = Node(testUUID2)
    val relation = Relation(testUUID3, node1, node2)

    val graph = Graph(graphId, Seq(node1, node2), Seq(relation))
    val validatedGraph = GraphValidator.validate(graph)

    validatedGraph.isValid shouldBe true
  }

  test("should return error with duplicated ids") {
    val node1 = Node(testUUID1)
    val node2 = Node(testUUID1)

    val graph = Graph(graphId, Seq(node1, node2), Nil)
    val validatedGraph = GraphValidator.validate(graph)

    validatedGraph.isInvalid shouldBe true
    errors(validatedGraph) should contain theSameElementsAs Seq(ElementIdNotUnique(Seq(node1, node2)))
  }

  test("should return multiple duplicated ids errors") {
    val node1 = Node(testUUID1)
    val node2 = Node(testUUID1)
    val node3 = Node(testUUID2)
    val node4 = Node(testUUID2)

    val graph = Graph(graphId, Seq(node1, node2, node3, node4), Nil)
    val validatedGraph = GraphValidator.validate(graph)

    val duplicateError1 = ElementIdNotUnique(Seq(node1, node2))
    val duplicateError2 = ElementIdNotUnique(Seq(node3, node4))

    validatedGraph.isInvalid shouldBe true
    errors(validatedGraph) should contain theSameElementsAs Seq(duplicateError1, duplicateError2)
  }

  test("should return missing node error") {
    val node1 = Node(testUUID1)
    val node2 = Node(testUUID2)
    val relation = Relation(testUUID3, node1, node2)

    val graph = Graph(graphId,Seq(node1), Seq(relation))
    val validatedGraph = GraphValidator.validate(graph)

    validatedGraph.isInvalid shouldBe true
    errors(validatedGraph) should contain theSameElementsAs Seq(MissingNodeError(node2))
  }

  test("should return multiple missing node errors") {
    val node1 = Node(testUUID1)
    val node2 = Node(testUUID2)
    val relation = Relation(testUUID3, node1, node2)

    val graph = Graph(graphId, Nil, Seq(relation))
    val validatedGraph = GraphValidator.validate(graph)

    validatedGraph.isInvalid shouldBe true
    errors(validatedGraph) should contain theSameElementsAs Seq(
      MissingNodeError(node1),
      MissingNodeError(node2)
    )
  }

  test("should return duplicate relation error") {
    val node1 = Node(testUUID1)
    val node2 = Node(testUUID2)
    val relation1 = Relation(testUUID3, node1, node2)
    val relation2 = Relation(testUUID4, node1, node2)

    val graph = Graph(graphId, Seq(node1, node2), Seq(relation1, relation2))
    val validatedGraph = GraphValidator.validate(graph)

    validatedGraph.isInvalid shouldBe true
    errors(validatedGraph) should contain theSameElementsAs Seq(RelationNotUnique(Seq(relation1, relation2)))
  }

}

object ValidatorTestUtils {
  def errors[A](validated: Validated[Seq[A], _]): Seq[A] = {
    validated.toEither.left.getOrElse(Seq.empty)
  }
}
