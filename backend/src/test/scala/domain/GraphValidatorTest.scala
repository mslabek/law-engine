package domain

import cats.syntax.all._
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import java.util.UUID

class GraphValidatorTest extends AnyFunSuite with Matchers {

  private val testUUID1 = UUID.fromString("00000000-0000-0000-0000-000000000001")
  private val testUUID2 = UUID.fromString("00000000-0000-0000-0000-000000000002")

  test("should not return any errors for valid graph") {
    val node1 = Node(testUUID1)
    val node2 = Node(testUUID2)

    val graph = Graph(Seq(node1, node2), Nil)
    val validatedGraph = GraphValidator.validate(graph)

    validatedGraph.isValid shouldBe true
  }

  test("should return error with duplicated ids") {
    val node1 = Node(testUUID1)
    val node2 = Node(testUUID1)

    val graph = Graph(Seq(node1, node2), Nil)
    val validatedGraph = GraphValidator.validate(graph)

    validatedGraph.isInvalid shouldBe true
    validatedGraph shouldBe ElementIdsNotUnique(Seq(testUUID1)).invalidNec
  }

}
