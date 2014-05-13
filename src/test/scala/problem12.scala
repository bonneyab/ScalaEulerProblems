import org.scalatest._
import org.scalatest.matchers.ShouldMatchers

class Problem12Spec extends FlatSpec with ShouldMatchers {
    "problem 12" should "return 28 for 5" in {
    val problem = Problem12
    val result = problem.getFirstTriangleNumberWithNumberOfDivisors(5)
    result should be === 28
  }
  // "problem 10" should "return 142913828922 for 2 million" in {
  // 	val problem = Problem10
  // 	val result = problem.GetSumOfPrimeNumbersBelowNUmber(2000000)
  //   result should be === 142913828922.0
  // }
}
