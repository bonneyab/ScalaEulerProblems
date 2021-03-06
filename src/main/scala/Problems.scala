import scala.annotation.tailrec
import scala.collection.immutable
import scala.collection.mutable


//I KNOW I KNOW THIS SHOULD BE A BUNCH OF FILES I sort of like seeing it all together though.

//Multiples of 3 and 5
//Problem 1
//If we list all the natural numbers below 10 that are multiples of 3 or 5, we get 3, 5, 6 and 9. The sum of these multiples is 23.
//Find the sum of all the multiples of 3 or 5 below 1000.
object Problem1 {
  def sumOfMultiplesBelowNumber(end : Int, multiples : Array[Int]) : Int = {
    (0 until end).filter(x => multiples.exists(x % _ == 0)).sum
  }
}

//Even Fibonacci numbers
//Problem 2
//Each new term in the Fibonacci sequence is generated by adding the previous two terms. By starting with 1 and 2, the first 10 terms will be:
//1, 2, 3, 5, 8, 13, 21, 34, 55, 89, ...
//By considering the terms in the Fibonacci sequence whose values do not exceed four million, find the sum of the even-valued terms.
object Problem2 {
  def fibonacciGetSumBelowMax(max: Int)  : Int = {
    @tailrec def fibonacciSumBelowMaxWithTotal(prev: Int, current: Int, max: Int, total: Int) : Int = {
      val next = prev + current
      if(next > max){
        return total
      }
      return fibonacciSumBelowMaxWithTotal(current, next, max, if(next%2 == 0) total + next else total )
    }
    fibonacciSumBelowMaxWithTotal(0, 1, max, 0)
  }
}

//Ok, tc might be the most clear function name but that inner fuction is only for the purposes of tail re

//Largest prime factor
//Problem 3
//The prime factors of 13195 are 5, 7, 13 and 29.
//What is the largest prime factor of the number 600851475143 ?
object Problem3 {
  def getLargestPrimeFactor(number: Int)  : Int = {
    @tailrec def tc(number: Int, max: Int, current: Int) : Int = 
    number match {
      case x if x < current => max
      case x if x % current == 0 => tc(number/current, current, current + 1)
      case x => tc(number, max, current + 1)
    }
    tc(number, 1, 2)
  }
}

//Largest palindrome product
//Problem 4
//A palindromic number reads the same both ways. The largest palindrome made from the product of two 2-digit numbers is 9009 = 91 × 99.
//Find the largest palindrome made from the product of two 3-digit numbers.
object Problem4 {
  //A good example of something that could probably use some ocmments...
  def getLargestPalindromeProduct(number: Int)  : Int = {
    @tailrec def tc(left: Int, right: Int, current: Int, mark: Int) : Int = {
      //println(s"left $left, right $right, current $current, mark $mark")
      val next = right * left
      if(left < 1 || left < mark)
        current
      else if(isPalindrome(next) && next > current){
        tc(left -1, number, next, right)
      }
      else if(right > 0){
        tc(left, right - 1, current, mark)
      }
      else{
        tc(left-1, number, current, mark)
      }
    }

    tc(number, number, 0, 0)
  }

  def isPalindrome(n: Int) : Boolean = {
    n.toString.reverse == n.toString
  }
}

//Smallest multiple
//Problem 5
//2520 is the smallest number that can be divided by each of the numbers from 1 to 10 without any remainder.
//What is the smallest positive number that is evenly divisible by all of the numbers from 1 to 20?
object Problem5 {
  def getSmallestNumberDivisibleByNumbers(number: Int) : Int = {
    val factors = (1 to number).map{ x => getFactors(x)}
    val mergedFactors = (new immutable.HashMap[Int, Int]() /: factors)(mergeMaps(_, _))
    //why doesn't this work?
    //mergedFactors./:(1)(a, (k1, v1)) => a * math.pow(k1, v1).toInt)
    (1 /: mergedFactors){case (a, (k1, v1)) => a * math.pow(k1, v1).toInt}
  }

  def getFactors(number: Int) : immutable.HashMap[Int, Int] = {
    @tailrec def tc(number: Int, counter: Int, result: immutable.HashMap[Int, Int]) : immutable.HashMap[Int, Int] = {
      if(counter > number){
        return result
      }
      if(number % counter == 0){
        if(result.contains(counter)){
          tc(number/counter, counter, mergeMaps(result, immutable.HashMap[Int, Int](counter -> (result(counter) + 1))))
        }
        else{
          tc(number/counter, counter, mergeMaps(result, immutable.HashMap[Int, Int](counter -> 1)))
        }
      }
      else{
        tc(number, counter + 1, result)
      }
    }

    tc(number, 2, new immutable.HashMap[Int, Int])
  }

  def mergeMaps(a : immutable.HashMap[Int, Int], b: immutable.HashMap[Int, Int]) : immutable.HashMap[Int, Int] = {
    a.merged(b){
      case ((k1, v1), (k2, v2)) => ((k1, math.max(v1, v2)))
    }
  }
}

//The sum of the squares of the first ten natural numbers is,
//12 + 22 + ... + 102 = 385
//The square of the sum of the first ten natural numbers is,
//(1 + 2 + ... + 10)2 = 552 = 3025
//Hence the difference between the sum of the squares of the first ten natural numbers and the square of the sum is 3025 − 385 = 2640.
//Find the difference between the sum of the squares of the first one hundred natural numbers and the square of the sum.
object Problem6 {
  def sumOfSquares(end: Int) : Int = {
    (1 to end).map{math.pow(_, 2)}.sum.toInt
  }

  def squareOfSums(end: Int) : Int = {
    math.pow((1 to end).sum, 2).toInt
  }
}

//10001st prime
//Problem 7
//By listing the first six prime numbers: 2, 3, 5, 7, 11, and 13, we can see that the 6th prime is 13.
//What is the 10 001st prime number?
object Problem7{
  def getPrimeNumber(number: Int) : Int = {
    @tailrec def tc(n: Int, counter: Int, current: Int) : Int = {
      if(n < 0) return current
      else if(PrimeHelper.isPrime(counter)) tc(n - 1, counter + 1, counter)
      else tc(n, counter + 1, current)
    }

    tc(number, 1, 0)
  }
}

/*Find the greatest product of five consecutive digits in the 1000-digit number. 

73167176531330624919225119674426574742355349194934
96983520312774506326239578318016984801869478851843
85861560789112949495459501737958331952853208805511
12540698747158523863050715693290963295227443043557
66896648950445244523161731856403098711121722383113
62229893423380308135336276614282806444486645238749
30358907296290491560440772390713810515859307960866
70172427121883998797908792274921901699720888093776
65727333001053367881220235421809751254540594752243
52584907711670556013604839586446706324415722155397
53697817977846174064955149290862569321978468622482
83972241375657056057490261407972968652414535100474
82166370484403199890008895243450658541227588666881
16427171479924442928230863465674813919123162824586
17866458359124566529476545682848912883142607690042
24219022671055626321111109370544217506941658960408
07198403850962455444362981230987879927244284909188
84580156166097919133875499200524063689912560717606
05886116467109405077541002256983155200055935729725
71636269561882670428252483600823257530420752963450*/
object Problem8 {
  def getMaxMultipleFromStringNumber(number: String) : Int = {
    val numberArray = number.toCharArray.map { _.asDigit}
    @tailrec def tc(numbers: Array[Int], max: Int) : Int = {
      if(numbers.length < 5) return max
      val result = (1 /: numbers.take(5)){ _ * _ }
      tc(numbers.drop(1), if(result > max) result else max)
    }
    tc(numberArray, 0)
  }
}

//  A Pythagorean triplet is a set of three natural numbers, a < b < c, for which,
//a^2 + b^2 = c^2
//For example, 32 + 42 = 9 + 16 = 25 = 5^2.
//There exists exactly one Pythagorean triplet for which a + b + c = 1000.
//Find the product abc.
object Problem9 {
  def getProductOfPythagoreanTripletForNumber(number: Int) : Int = {
    @tailrec def tc(a : Int, b: Int, c: Int) : Int = {
      if(math.pow(a, 2) + math.pow(b, 2) == math.pow(c, 2)){
        return a * b * c
      }
      if(a >= b) return 0

      if(a + 1 < b - 1)
        tc(a + 1, b - 1, c)
      else if(number - c + 1 >= c - 1)
        tc(number - (c * 2 -3), c - 2, c -1 )
      else
        tc(0, number - c + 1, c - 1)
    }
    tc(0, 1, number - 1)
  }
}

//The sum of the primes below 10 is 2 + 3 + 5 + 7 = 17.
//Find the sum of all the primes below two million.
object Problem10{
    def getSumOfPrimeNumbersBelowNUmber(number: Int) : Double = {
      (0.0 /: PrimeHelper.getPrimeNumbersBelowNumber(number)) { _ + _ }
    }
}

// Highly divisible triangular number
// Problem 12
// The sequence of triangle numbers is generated by adding the natural numbers. So the 7th triangle number would be 1 + 2 + 3 + 4 + 5 + 6 + 7 = 28. The first ten terms would be:
// 1, 3, 6, 10, 15, 21, 28, 36, 45, 55, ...
// Let us list the factors of the first seven triangle numbers:
//  1: 1
//  3: 1,3
//  6: 1,2,3,6
// 10: 1,2,5,10
// 15: 1,3,5,15
// 21: 1,3,7,21
// 28: 1,2,4,7,14,28
// We can see that 28 is the first triangle number to have over five divisors.
// What is the value of the first triangle number to have over five hundred divisors?
object Problem12 {
    def getFirstTriangleNumberWithNumberOfDivisors(numberOfDivisors: Int) : Int = {
      val getTriangleNumber = (x: Int) => x * (x+1)/2
      val getDivisors = (x: Int) => (1 to x).filter(x%_ == 0)

      @tailrec def tc(number: Int) : Int = {
        val triangleNumber = getTriangleNumber(number)
        if(getDivisors(triangleNumber).length > numberOfDivisors)
          return triangleNumber

        tc(number + 1)
      }

      tc(1)
    }
}

// Longest Collatz sequence
// Problem 14
// The following iterative sequence is defined for the set of positive integers:
// n → n/2 (n is even)
// n → 3n + 1 (n is odd)
// Using the rule above and starting with 13, we generate the following sequence:
// 13 → 40 → 20 → 10 → 5 → 16 → 8 → 4 → 2 → 1
// It can be seen that this sequence (starting at 13 and finishing at 1) contains 10 terms. Although it has not been proved yet (Collatz Problem), it is thought that all starting numbers finish at 1.
// Which starting number, under one million, produces the longest chain?
// NOTE: Once the chain starts the terms are allowed to go above one million.
object Problem14 {
  var _computedValues = new mutable.HashMap[BigInt, Int]
  def getLongestCollatzSequenceUnderNumber(number: Int) : Int = {
    ((1, 1) /: (number to 1 by -1)) ((i, m) => 
        getCollatzTermLength(m) match {
          case x if x > i._1 => (x, m)
          case _ => i
        }
      )._2
  }

  @tailrec def getCollatzTermLength(number: BigInt, total: Int = 1) : Int = {
    if(_computedValues.contains(number)){
        _computedValues.put(number, (total - 1 + _computedValues(number)))
        return total - 1 + _computedValues(number)
    }
    number match {
      case x if x == 1 => total
      case x if x % 2 == 0 => getCollatzTermLength(x/2, total + 1)
      case x => getCollatzTermLength(x * 3 + 1, total + 1)
    }
  }
}


// Power digit sum
// Problem 16
// 2^15 = 32768 and the sum of its digits is 3 + 2 + 7 + 6 + 8 = 26.

// What is the sum of the digits of the number 2^1000?
object Problem16 {
  def getSumOfPowerDigits(number: BigInt, power: Int) : Int = {
    val bigNumber = number.pow(power)
    val powerArray = bigNumber.toString.toCharArray.map{ _.asDigit}
    (0 /: powerArray) (_ + _)
  }
}

// You are given the following information, but you may prefer to do some research for yourself.

// 1 Jan 1900 was a Monday.
// Thirty days has September,
// April, June and November.
// All the rest have thirty-one,
// Saving February alone,
// Which has twenty-eight, rain or shine.
// And on leap years, twenty-nine.
// A leap year occurs on any year evenly divisible by 4, but not on a century unless it is divisible by 400.
// How many Sundays fell on the first of the month during the twentieth century (1 Jan 1901 to 31 Dec 2000)?
  
object Problem19 {
  case class CustomDate (year: Int, month: Int, dayOfMonth: Int, dayOfWeek: Int)
  
  def getSundaysInTwentiethCentury() : Int = {
    @tailrec def getCustomDaysBeforeYear(year: Int, items: List[CustomDate]) : List[CustomDate] = {
      val nextDay = getNextDay(items.head);
      if(nextDay.year == year) items;
      else getCustomDaysBeforeYear(year, nextDay :: items)
    }

    val allDays = getCustomDaysBeforeYear(2001, List(new CustomDate(1900, 1, 1, 1)))

    allDays.count(a => a.dayOfMonth == 1 && a.dayOfWeek == 7 && a.year >= 1901)
  }

  def getNextDay(date: CustomDate) : CustomDate = {
    val dayOfWeek = if(date.dayOfWeek < 7) date.dayOfWeek + 1 else 1
    val dayOfMonth = if(date.dayOfMonth < getDaysInMonth(date.month, date.year)) date.dayOfMonth + 1 else 1
    val year = if(date.month == 12 && dayOfMonth == 1) date.year + 1 else date.year
    val month = dayOfMonth match {
      case x if x == 1 => if(date.month == 12) 1 else date.month + 1
      case _ => date.month
    }

    return new CustomDate(year, month, dayOfMonth, dayOfWeek)
  }

  def getDaysInMonth(month: Int, year: Int) : Int = {
    val longMonths = List(1,3,5,7,8,10,12)
    month match{
      case 2 => if(isLeapYear(year)) 29 else 28
      case x if longMonths.exists(_ == x) => 31
      case _ => 30
    }
  }

  def isLeapYear(year: Int) : Boolean = year match {
    case x if x % 400 == 0 => true
    case x if x % 100 == 0 => false
    case x => x % 4 == 0
  }
}

// Factorial digit sum
// Problem 20
// n! means n × (n − 1) × ... × 3 × 2 × 1
// For example, 10! = 10 × 9 × ... × 3 × 2 × 1 = 3628800,
// and the sum of the digits in the number 10! is 3 + 6 + 2 + 8 + 8 + 0 + 0 = 27.
// Find the sum of the digits in the number 100!
object Problem20 {
  def getSumOfFactorial(number: BigInt) : Int = {
    val bigNumber = factorial(number)
    val powerArray = bigNumber.toString.toCharArray.map{ _.asDigit}
    (0 /: powerArray) (_ + _)
  }

  @tailrec def factorial(number: BigInt, total: BigInt = 1) : BigInt = {
    if(number == 1) return total
    factorial(number - 1, number * total)
  }
}

// Amicable numbers
// Problem 21
// Let d(n) be defined as the sum of proper divisors of n (numbers less than n which divide evenly into n).
// If d(a) = b and d(b) = a, where a ≠ b, then a and b are an amicable pair and each of a and b are called amicable numbers.
// For example, the proper divisors of 220 are 1, 2, 4, 5, 10, 11, 20, 22, 44, 55 and 110; therefore d(220) = 284. The proper divisors of 284 are 1, 2, 4, 71 and 142; so d(284) = 220.
// Evaluate the sum of all the amicable numbers under 10000.
object Problem21 {
  def getSumOfAmicableNumbersUnderNumber(number: Int) : Int = {
    val divisorTable = (1 to number).map(i => i -> getSumOfProperDivisors(i)).toMap
    def isAmicable(valueForKey: Option[Int], key: Int) : Boolean = valueForKey match {
      case Some(valueForKey) => valueForKey == key
      case None => false
    }

    val amicableNumbers = divisorTable.filter(d => d._1 != d._2 &&isAmicable(divisorTable.get(d._2), d._1)).map(d => d._1).toList.sortWith(_ < _)
    amicableNumbers.sum
  }

  def getSumOfProperDivisors(number: Int) : Int = {
    val factorList = FactorizationHelper.getFactorList(number)
    val divisorCombinations = (1 to factorList.length)
      .map(numberOfItems => factorList.combinations(numberOfItems).toList
      .map(listOfDivisors => listOfDivisors.reduceLeft(_ * _)))
    val properDivisors = ((List[Int]() /: divisorCombinations) (_ ::: _)).filter(d => d < number).distinct

    properDivisors.sum
  }
}

// Names scores
// Problem 22
// Using names.txt (right click and 'Save Link/Target As...'), a 46K text file containing over five-thousand first names, begin by sorting it into alphabetical order. Then working out the alphabetical value for each name, multiply this value by its alphabetical position in the list to obtain a name score.

// For example, when the list is sorted into alphabetical order, COLIN, which is worth 3 + 15 + 12 + 9 + 14 = 53, is the 938th name in the list. So, COLIN would obtain a score of 938 × 53 = 49714.

// What is the total of all the name scores in the file?
object Problem22 {
  val map = Map("a" -> 1, "b" -> 2, "c" -> 3, "d" -> 4, "e" -> 5, "f" -> 6, "g" -> 7, "h" -> 8, "i" -> 9, "j" -> 10, "k" -> 11,"l" -> 12,"m" -> 13, "n" -> 14, "o" -> 15, "p" -> 16, "q" -> 17, "r" -> 18,"s" -> 19, "t" -> 20, "u" -> 21, "v" -> 22,"w" -> 23, "x" -> 24, "y" -> 25, "z" -> 26)

  def calculateWord(word: String) : Int = {
    word.toLowerCase.toCharArray.map{ c => map(c.toString)}.sum
  }

  def parseFile() : Map[Int, String] = {
    val names = io.Source.fromFile("names.txt").mkString.replace("\"","").split(",").sorted.toArray
    (1 to names.length).map(n => n -> names(n-1)).toMap
  }

  def getTotalNameScore() : Int = {
    val names = parseFile()
    names.map{case (k, v) => k * calculateWord(v)}.sum
  }
}

// Non-abundant sums
// Problem 23
// A perfect number is a number for which the sum of its proper divisors is exactly equal to the number. For example, the sum of the proper divisors of 28 would be 1 + 2 + 4 + 7 + 14 = 28, 
//which means that 28 is a perfect number.
// A number n is called deficient if the sum of its proper divisors is less than n and it is called abundant if this sum exceeds n.
// As 12 is the smallest abundant number, 1 + 2 + 3 + 4 + 6 = 16, the smallest number that can be written as the sum of two abundant numbers is 24. By mathematical analysis, 
//it can be shown that all integers greater than 28123 can be written as the sum of two abundant numbers. However, this upper limit cannot be reduced any further by analysis 
//even though it is known that the greatest number that cannot be expressed as the sum of two abundant numbers is less than this limit.
// Find the sum of all the positive integers which cannot be written as the sum of two abundant numbers.
object Problem23 {
  def isAbundant(number: Int) : Boolean = {
    return Problem21.getSumOfProperDivisors(number) > number
  }

//THIS IS SLLLLLLLLLLLLOOOOOOOOOOOOOOOOOOWWWWWWWWWWWWWWWW
  def getSumOfNotSumOfAbundantNumbers() : Int = {
    val abundantNumbers = (1 to 28123).filter(isAbundant).toList
    val immutable = (1 to 28123).map(x => x -> false)
    val canBeWrittenasAbundant = mutable.Map(immutable.toSeq: _*) 
    @tailrec def setCanBeWrittenAsAbundant(outter: List[Int], inner: List[Int])  {
      (outter, inner) match {
        case(x, y) if x.length == 0 => return
        case(x, y) if y.length == 0 => setCanBeWrittenAsAbundant(x.tail, abundantNumbers)
        case(x, y) if x.head + y.head > 28123 => setCanBeWrittenAsAbundant(x.tail, abundantNumbers)
        case(x, y) => {
          canBeWrittenasAbundant(x.head + y.head) = true
          setCanBeWrittenAsAbundant(x, y.tail)
        }
      }
    }

    setCanBeWrittenAsAbundant(abundantNumbers, abundantNumbers)
    canBeWrittenasAbundant.filter(!_._2).map(_._1).sum
  }
}

// The Fibonacci sequence is defined by the recurrence relation:
// Fn = Fn−1 + Fn−2, where F1 = 1 and F2 = 1.
// Hence the first 12 terms will be:
// F1 = 1
// F2 = 1
// F3 = 2
// F4 = 3
// F5 = 5
// F6 = 8
// F7 = 13
// F8 = 21
// F9 = 34
// F10 = 55
// F11 = 89
// F12 = 144
// The 12th term, F12, is the first term to contain three digits.
// What is the first term in the Fibonacci sequence to contain 1000 digits?
object Problem25 {
  //Best name ever?
  //Consider combining some logic with problem 2
  def fibonacciTermForDigits(digits: Int) : Int = {
    @tailrec def bigFib(prev: BigInt, current: BigInt, termCount: Int) : Int = {
      val next = prev + current
      if(getDigitsFromNumber(next) >= digits){
        return termCount + 1
      }
      return bigFib(current, next, termCount + 1)
    }
    bigFib(0, 1, 1)
  }

  def getDigitsFromNumber(number: BigInt) : Int = {
    return number.toString.length
  }
}


// Euler discovered the remarkable quadratic formula:
// n² + n + 41
// It turns out that the formula will produce 40 primes for the consecutive values n = 0 to 39. However, when n = 40, 402 + 40 + 41 = 40(40 + 1) + 41 is divisible by 41, and certainly when n = 41, 41² + 41 + 41 is clearly divisible by 41.
// The incredible formula  n² − 79n + 1601 was discovered, which produces 80 primes for the consecutive values n = 0 to 79. The product of the coefficients, −79 and 1601, is −126479.
// Considering quadratics of the form:
// n² + an + b, where |a| < 1000 and |b| < 1000
// where |n| is the modulus/absolute value of n
// e.g. |11| = 11 and |−4| = 4
// Find the product of the coefficients, a and b, for the quadratic expression that produces the maximum number of primes for consecutive values of n, starting with n = 0.
object Problem27 {
  val primes = PrimeHelper.getPrimeNumbersBelowNumber(100000)

  def GetCoefficientsForQuadraticExpression(max: Int) : Int = {
    @tailrec def doStuff(a: Int, b:Int, totalPrimes:Int, coefficientProduct:Int) : Int = {
      val primeCount = GetPrimeCount(a, b)
      def next(count: Int) : Int = count
      //This is sort of sucky....
      if(primeCount > totalPrimes){
        primeCount match {
          case x if b > max => {println(s"Returning a: $a, b: $b total: $totalPrimes"); return a * b}
          case x if a < max => doStuff(a + 1, b, x, a * b)
          case x => doStuff(-max, b + 1, x, a * b)
        }
      }
      else{
        totalPrimes match {
          case x if b > max => {println(s"Returning a: $a, b: $b total: $totalPrimes");return coefficientProduct}
          case x if a < max => doStuff(a + 1, b, x, coefficientProduct)
          case x => doStuff(-max, b + 1, x, coefficientProduct)
        }
      }
    }

    doStuff(-max, -max, 0, 0)
  }


  def GetPrimeCount(a: Int, b: Int) : Int = {
    @tailrec def countPrimes(number: Int, count: Int) : Int = {
      val result = math.pow(number, 2) + a*number + b
      //val isPrime = PrimeHelper.isPrime(result.toInt)
      if(!primes.contains(result)){
        return count
      }
      else{
        countPrimes(number + 1, count + 1)
      }
    }

    countPrimes(0, 0)
  }
}

// Problem 31
// In England the currency is made up of pound, £, and pence, p, and there are eight coins in general circulation:
// 1p, 2p, 5p, 10p, 20p, 50p, £1 (100p) and £2 (200p).
// It is possible to make £2 in the following way:
// 1×£1 + 1×50p + 2×20p + 1×5p + 1×2p + 3×1p
// How many different ways can £2 be made using any number of coins?
//TODO: This took quite a few iterations and was a headache, I should remind myself how this works on occasion
object Problem31{
  def GetTotalCombintationsForNumber(increments : Array[Int], total: Int) : Int = {
    //Todo redo with val and build out the array?
    var calculatedValues = new Array[Int](total + 1)
    calculatedValues(0) = 1
    @tailrec def calculateCombinations(incrementIndex : Int, valueToCalculate : Int) : Array[Int] = {
      calculatedValues(valueToCalculate) = calculatedValues(valueToCalculate) + calculatedValues(valueToCalculate - increments(incrementIndex))
      //This duplicate if statement stuff because of the tailrec.
      val theEnd = valueToCalculate >= total
      if(incrementIndex == increments.length - 1 && theEnd){
        return calculatedValues;
      }
      if(theEnd){
        calculateCombinations(incrementIndex + 1, increments(incrementIndex + 1))
      }
      else{
        calculateCombinations(incrementIndex, valueToCalculate + 1)
      }
    }

    calculateCombinations(0, increments(0))
    return calculatedValues(total)
  }
}


  // Circular primes
  // Problem 35
  // The number, 197, is called a circular prime because all rotations of the digits: 197, 971, and 719, are themselves prime.
  // There are thirteen such primes below 100: 2, 3, 5, 7, 11, 13, 17, 31, 37, 71, 73, 79, and 97.
  // How many circular primes are there below one million?
  object Problem35 {
    def GetCircularPrimeCountBelowNumber(number: Int) : Int = {
      val circularPrimes = PrimeHelper.getPrimeNumbersBelowNumber(number).filter(isCircularPrime)
      circularPrimes.foreach(println)
      return circularPrimes.length
    }

    def isCircularPrime(number : Int) : Boolean = {
      val stringNumber = number.toString
      //doh, evidently it really meant rotations, not all permutations, change this eventually?
      val combinations = stringNumber.permutations
     //probably go faster by turning the previously calculated primes into a hashtable.
      return combinations.forall(c => PrimeHelper.isPrime(c.toInt))
    }
  }