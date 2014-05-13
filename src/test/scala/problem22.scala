import org.scalatest._
import org.scalatest.matchers.ShouldMatchers

class Problem22Spec extends FlatSpec with ShouldMatchers {
  "problem 22" should "return 19 for ADAM" in {
    val problem = Problem22
    val result = problem.calculateWord("ADAM")
    result should be === 19
  }
    "problem 22" should "return 871198282 for TotalScore" in {
    val problem = Problem22
    val result = problem.getTotalNameScore()
    result should be === 871198282
  }
}
