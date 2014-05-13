import org.scalatest._
import org.scalatest.matchers.ShouldMatchers

class Problem21Spec extends FlatSpec with ShouldMatchers {
  "problem 21" should "return 284 for 220" in {
    val problem = Problem21
    val result = problem.getSumOfProperDivisors(220)
    result should be === 284
  }
  "problem 21" should "return ???" in {
    val problem = Problem21
    val result = problem.getSumOfAmicableNumbersUnderNumber(10000)
    result should be === 31626
  }
}
