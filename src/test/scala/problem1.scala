import org.scalatest._
import org.scalatest.matchers.ShouldMatchers

class Problem1Spec extends FlatSpec with ShouldMatchers {
  "problem 1" should "return 23 for 10" in {
  	val problem = Problem1
  	val result = problem.sumOfMultiplesBelowNumber(10, Array(3,5))
    result should be === 23
  }
}
	