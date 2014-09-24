import org.scalatest._
import org.scalatest.matchers.ShouldMatchers

class Problem35Spec extends FlatSpec with ShouldMatchers {

  "problem 35 GetTotalCombintationsForNumber" should "return 13 for 100" in {
    val result = Problem35.GetCircularPrimeCountBelowNumber(100)
    result should be === 13
  }

    "problem 35 GetTotalCombintationsForNumber" should "return ??? for 1000000" in {
    val result = Problem35.GetCircularPrimeCountBelowNumber(1000000)
    result should be === 22
  }
}
