import org.scalatest._
import org.scalatest.matchers.ShouldMatchers

class Problem2Spec extends FlatSpec with ShouldMatchers {
  "problem 2" should "return 44 for 11" in {
  	val problem = Problem2
  	val result = problem.fibonacciGetSumBelowMax(100)
    result should be === 44
  }
}
