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

object PrimeHelper {
  def isPrime(number: Int) : Boolean = {
    @tailrec def tc(n: Int, counter: Int) : Boolean = {
      if(counter >= n) return true
      if(n % counter == 0) return false;
      tc(n, counter + 1)
    }
    tc(number, 2)
  }

  def getPrimeNumbersBelowNumber(number: Int) : List[Int] = {
    var numbers = (2 to number).map { n => (n, true) } toMap

    (2 to math.ceil(math.pow(number, .5)).toInt + 1).foreach { n =>
      if(numbers(n)) {
        @tailrec def tc(current: Int, counter: Int, map: Map[Int, Boolean] ) : Map[Int, Boolean] = {
          if(current > number){
            return map
          }

          tc(math.pow(n, 2).toInt + n * counter, counter + 1, map.updated(current, false))
        }
        numbers = tc(math.pow(n, 2).toInt , 1, numbers)
      }
    }

    numbers.filter(_._2).map{ case (k, v) => k }.toList
  }
}