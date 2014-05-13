import org.scalatest._
import org.scalatest.matchers.ShouldMatchers

class Problem4Spec extends FlatSpec with ShouldMatchers {
  "problem 4" should "return 9009 for 99" in {
  	val problem = Problem4
  	val result = problem.getLargestPalindromeProduct(99)
    result should be === 9009
  }
}
