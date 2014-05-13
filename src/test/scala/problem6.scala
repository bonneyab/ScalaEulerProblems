import org.scalatest._
import org.scalatest.matchers.ShouldMatchers

class Problem6Spec extends FlatSpec with ShouldMatchers {
  "problem 6" should "return 2640 for 10" in {
  	val problem = Problem6
  	val squareOfSums = problem.squareOfSums(10)
    val sumOfSquares = problem.sumOfSquares(10)
    squareOfSums should be === 3025
    sumOfSquares should be === 385
    (squareOfSums - sumOfSquares) should be === 2640
  }
}
