package dog.shebang.recursion

import scala.annotation.tailrec
import scala.util.control.TailCalls
import scala.util.control.TailCalls.TailRec
import scala.util.Try
import scala.util.Failure
import scala.util.Success

object Recursion:
    def recursion(value: Int): Int = Try[Int] {
        if value == 0 then 0
        else value + recursion(value - 1)
    }.getOrElse { 1 }
    
    end recursion

    def tailrecursion(value: Int, result: Int = 0): Int =
        if value == 0 then result
        else tailrecursion(value - 1, result + value)
    end tailrecursion

    @tailrec
    def tailrecursionWithAnnotation(value: Int, result: Int = 0): Int =
        if value == 0 then result
        else tailrecursionWithAnnotation(value - 1, result + value)
    end tailrecursionWithAnnotation

    def continuousPassingStyle(
        value: Int,
        continuous: Int => Int = value => value,
    ): Int = 
        if value == 0 then continuous(0)
        else continuousPassingStyle(
            value - 1,
            result => continuous(result + value),
        )
    end continuousPassingStyle

    def recursionWithTailRec(value: Int): Int =
        def go(value: Int): TailRec[Int] = 
            if value == 0 then TailCalls.done(0)
            else TailCalls.tailcall(go(value - 1)).map(result => result + value)
        end go

        go(value).result
    end recursionWithTailRec

end Recursion

import org.scalatest.funspec.AnyFunSpec
import org.scalatest.diagrams.Diagrams

class RecursionTest extends AnyFunSpec with Diagrams:
    describe("Recursion") {
        case class TestCase(input: Int, expected: Int)

        val maxTestCase = TestCase(Integer.MAX_VALUE, -1073741824)
        val normalTestCase = TestCase(10, 55)

        describe("recursion") {
            describe("failure: stack overflow") {
                it(s"should throw stack overflow error") {
                    assertThrows[StackOverflowError](
                        Recursion.recursion(maxTestCase.input)
                    )
                }
            }

            describe("success") {
                val TestCase(input, expected) = normalTestCase

                val result = Recursion.recursion(input)
                describe(s"when take with $input") {
                    it(s"should return $expected") {
                        assert(expected == result)
                    }
                }
            }
        }

        describe("tailrecursion") {
            describe("failure: stack overflow") {
                it(s"should throw stack overflow error") {
                    assertThrows[StackOverflowError] {
                        Recursion.tailrecursion(maxTestCase.input)
                    }
                }                
            }

            describe("success") {
                val TestCase(input, expected) = normalTestCase
                it(s"should return $expected") {
                    val result = Recursion.tailrecursion(input)
                    assert(expected == result)
                }
            }
        }

        describe("tailrecursionWithAnnotation") {
            describe("success") {
                val testCaseList = List[TestCase](
                    normalTestCase,
                    maxTestCase,
                )

                testCaseList.foreach { case TestCase(input, expected) => 
                    val result = Recursion.tailrecursionWithAnnotation(input)
                    it(s"should return $expected") {
                        assert(expected == result)
                    }
                }
            }
        }

        describe("continuousPassingStyle") {
            describe("failure: stack overflow") {
                it(s"should throw stack overflow error") {
                    assertThrows[StackOverflowError](
                        Recursion.continuousPassingStyle(maxTestCase.input)
                    )
                }
            }

            describe("success") {
                val TestCase(input, expected) = normalTestCase
                val result = Recursion.continuousPassingStyle(input)
                describe(s"when take with $input") {
                    it(s"should return $expected") {
                        assert(expected == result)
                    }
                }
            }
        }

        describe("recursionWithTailRec") {
            describe("success") {
                val testCaseList = List[TestCase](
                    normalTestCase,
                    maxTestCase,
                )
                testCaseList.foreach { case TestCase(input, expected) =>
                    it(s"should return $expected") {
                        val result = Recursion.recursionWithTailRec(input)
                        assert(expected == result)
                    }
                }
            }
        }
    }
end RecursionTest