import scala.annotation.tailrec
import scala.collection.immutable
import scala.collection.mutable


//Yeah yeah, helpers is vague and promotes large classes
object FactorizationHelper {
  def getFactorList(number: Int) : List[Int] = {
    val factors = Problem5.getFactors(number)
    //Ok ok, this is a little ghetto, my previous get factors gets them in an awkward format for other problems and doesn't include 1.
    1 :: (List[Int]() /: factors) {
      case (overAllList, (key, value)) => {
        (List[Int]() /: (0 until value)) {(listForPair, n) => key :: listForPair} ::: overAllList
      }
    }
  }
}
