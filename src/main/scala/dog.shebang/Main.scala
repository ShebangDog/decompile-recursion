import dog.shebang.recursion.Recursion
import scala.util.Try
import scala.util.Failure
import scala.util.Success

object Main {
  def main(args: Array[String]): Unit = {
    // val input = 5000
    // val max = Int.MaxValue
    
    // 工夫のない普通の再帰 5000 -> stack overflow
    // Recursion.recursion(input)

    // 末尾再帰 5000 -> OK, max -> OK
    // Recursion.tailrecursion(input)

    // 最適化ありの末尾再帰 5000 -> OK, max -> OK
    // Recursion.tailrecursionWithAnnotation(max)

    // 5000 -> NG
    // Recursion.continuousPassingStyle(input)

    // TailRecをもちいた再帰 5000 -> OK, max -> もうすこしでout of memory
    // Recursion.recursionWithTailRec(input)

    loop(10, function2)

    recurse(10, function2)
  }

  def loop[A](initialCount: Int, func: () => A): Unit = {
    var count = initialCount
    while(true) {
      if (count == 0) return

      func()
      count -= 1
    }
  }

  def preventOptimization[A](a: A): A = {
    a
  }

  def recurse[A](count: Int, func: () => A): Unit = {
    if (count == 0) return
    
    func()
    preventOptimization(
      recurse(count - 1, func)
    )
  }

  def function2(): Int = 
    3
  end function2
}
