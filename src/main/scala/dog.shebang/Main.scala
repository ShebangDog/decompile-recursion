import dog.shebang.recursion.Recursion
import scala.util.Try
import scala.util.Failure
import scala.util.Success

@main
def main() = 
  val input = 5000
  val max = Int.MaxValue
  
  // 工夫のない普通の再帰 5000 -> stack overflow
  // Recursion.recursion(input)

  // 末尾再帰 5000 -> OK, max -> OK
  // Recursion.tailrecursion(input)

  // 最適化ありの末尾再帰 5000 -> OK, max -> OK
  // Recursion.tailrecursionWithAnnotation(max)

  // 5000 -> NG
  // Recursion.continuousPassingStyle(input)

  // TailRecをもちいた再帰 5000 -> OK, max -> もうすこしでout of memory
  Recursion.recursionWithTailRec(input)

  
end main
