import org.scalatest._
import org.scalatest.matchers.ShouldMatchers

class Problem25Spec extends FlatSpec with ShouldMatchers {
  "problem 25 fibonacciTermForDigits" should "return 12 for 3" in {
    val result = Problem25.fibonacciTermForDigits(3)
    result should be === 12
  }
  "problem 25 fibonacciTermForDigits" should "return 476 for 1000" in {
    val result = Problem25.fibonacciTermForDigits(1000)
    result should be === 4782
  }
}
