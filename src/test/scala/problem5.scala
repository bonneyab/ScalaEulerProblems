import org.scalatest._
import org.scalatest.matchers.ShouldMatchers

class Problem5Spec extends FlatSpec with ShouldMatchers {
  "problem 5" should "return 2520 for 10" in {
  	val problem = Problem5
  	val result = problem.getSmallestNumberDivisibleByNumbers(10)
    result should be === 2520
  }
}
