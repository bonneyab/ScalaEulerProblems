import org.scalatest._
import org.scalatest.matchers.ShouldMatchers

class Problem10Spec extends FlatSpec with ShouldMatchers {
    "problem 10" should "return 17 for 10" in {
    val problem = Problem10
    val result = problem.getSumOfPrimeNumbersBelowNUmber(10)
    result should be === 17
  }
  "problem 10" should "return 142913828922 for 2 million" in {
  	val problem = Problem10
  	val result = problem.getSumOfPrimeNumbersBelowNUmber(2000000)
    result should be === 142913828922.0
  }
}
