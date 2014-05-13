import org.scalatest._
import org.scalatest.matchers.ShouldMatchers

class Problem16Spec extends FlatSpec with ShouldMatchers {
  "problem 16" should "return 26 for 2^15" in {
    val problem = Problem16
    val result = problem.getSumOfPowerDigits(2, 15)
    result should be === 26
  }
  "problem 16" should "return 1366 for 2^1000" in {
    val problem = Problem16
    val result = problem.getSumOfPowerDigits(2, 1000)
    result should be === 1366
  }
}
