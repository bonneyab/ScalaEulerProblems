import org.scalatest._
import org.scalatest.matchers.ShouldMatchers

class Problem7Spec extends FlatSpec with ShouldMatchers {
  "problem 7 IsPrime" should "return true for 23" in {
  	val problem = Problem7
  	val result = problem.isPrime(23)
    result should be === true
  }

  "problem 7 IsPrime" should "return false for 121" in {
    val problem = Problem7
    val result = problem.isPrime(121)
    result should be === false
  }

  "problem 7 GetPrimeNumber" should "return 13 for 6" in {
    val problem = Problem7
    val result = problem.getPrimeNumber(6)
    result should be === 13
  }

  "problem 7 GetPrimeNumber" should "return 104743 for 10001" in {
    val problem = Problem7
    val result = problem.getPrimeNumber(10001)
    result should be === 104743
  }
}
