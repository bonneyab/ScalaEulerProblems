import org.scalatest._
import org.scalatest.matchers.ShouldMatchers

class Problem14Spec extends FlatSpec with ShouldMatchers {
  "problem 14" should "return 9 for 13" in {
    val problem = Problem14
    val result = problem.getLongestCollatzSequenceUnderNumber(13)
    result should be === 9
  }
  // "problem 14" should "return 837799 for 999999" in {
  //   val problem = Problem14
  //   val result = problem.GetLongestCollatzSequenceUnderNumber(999999)
  //   result should be === 837799
  // }
  //Takes too long to constantly run
  "problem 14 GetCollatzTermLength" should "return 525 for 837799" in {
    val problem = Problem14
    val result = problem.getCollatzTermLength(837799)
    result should be === 525
  }
}
