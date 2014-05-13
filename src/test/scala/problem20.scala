import org.scalatest._
import org.scalatest.matchers.ShouldMatchers

class Problem20Spec extends FlatSpec with ShouldMatchers {
  "problem 20" should "return 3628800" in {
    val problem = Problem20
    val result = problem.factorial(10)
    result should be === 3628800
  }
  "problem 20" should "return 27" in {
    val problem = Problem20
    val result = problem.getSumOfFactorial(10)
    result should be === 27
  }
  "problem 20" should "return 648" in {
    val problem = Problem20
    val result = problem.getSumOfFactorial(100)
    result should be === 648
  }
}
