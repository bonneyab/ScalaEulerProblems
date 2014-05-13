import org.scalatest._
import org.scalatest.matchers.ShouldMatchers

class Problem19Spec extends FlatSpec with ShouldMatchers {
  "problem 19" should "return 171" in {
    val problem = Problem19
    val result = problem.getSundaysInTwentiethCentury()
    result should be === 171
  }
}
