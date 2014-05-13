import org.scalatest._
import org.scalatest.matchers.ShouldMatchers

class Problem9Spec extends FlatSpec with ShouldMatchers {
    "problem 9" should "return 31875000 for 1000" in {
    val problem = Problem9
    val result = problem.getProductOfPythagoreanTripletForNumber(1000)
    result should be === 31875000
  }
  "problem 9" should "return 60 for 12" in {
  	val problem = Problem9
  	val result = problem.getProductOfPythagoreanTripletForNumber(12)
    result should be === 60
  }
}
