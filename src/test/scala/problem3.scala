import org.scalatest._
import org.scalatest.matchers.ShouldMatchers

class Problem3Spec extends FlatSpec with ShouldMatchers {
  "problem 3" should "return 29 for 13195" in {
  	val problem = Problem3
  	val result = problem.getLargestPrimeFactor(13195)
    result should be === 29
  }
}
