import org.scalatest._
import org.scalatest.matchers.ShouldMatchers

class Problem31Spec extends FlatSpec with ShouldMatchers {

  "problem 31 GetTotalCombintationsForNumber" should "return 73682 for 200" in {
    // 1p, 2p, 5p, 10p, 20p, 50p, £1 (100p) and £2 (200p).
    val increments = Array(1, 2, 5, 10, 20, 50, 100, 200)
    val result = Problem31.GetTotalCombintationsForNumber(increments, 200)
    result should be === 73682
  }
}
