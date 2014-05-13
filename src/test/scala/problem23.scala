import org.scalatest._
import org.scalatest.matchers.ShouldMatchers

class Problem23Spec extends FlatSpec with ShouldMatchers {
  "problem 23 isAbundant" should "return true for 12" in {
    val result = Problem23.isAbundant(12)
    result should be === true
  }
  "problem 23 isAbundant" should "return false for 28" in {
    val result = Problem23.isAbundant(28)
    result should be === false
  }
  //This takes way too long to run.
  // "problem 23 getSumOfNotSumOfAbundantNumbers" should "return 4179871 for getSumOfNotSumOfAbundantNumbers" in {
  //   val result = Problem23.getSumOfNotSumOfAbundantNumbers()
  //   result should be === 4179871
  // }
}
