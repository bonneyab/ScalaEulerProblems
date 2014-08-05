import org.scalatest._
import org.scalatest.matchers.ShouldMatchers

class Problem27Spec extends FlatSpec with ShouldMatchers {
  "problem 27 GetPrimeCount" should "return 40 for 1,41" in {
    println(s"Getting prime count")
    val result = Problem27.GetPrimeCount(1, 41)
    result should be === 40
  }

  //This takes a minute or two to run slowing things down a fair bit.
  // "problem 27 GetCoefficientsForQuadraticExpression" should "return -59231 for 1000" in {
  //   println(s"Getting prime count")
  //   val result = Problem27.GetCoefficientsForQuadraticExpression(1000)
  //   result should be === -59231
  // }
}
