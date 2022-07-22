package lectures.part1basics

import scala.annotation.tailrec

object Recursion extends App {

  def factorial(number: Int): Int = {
    if (number <= 1) 1
    else {
      println("Computing factorial of " + number + " - I first need factorial of " + (number - 1))
      val result = number * factorial(number - 1)
      println("Computed factorial of " + number)
      result
    }
  }

  factorial(10)

  // Works with big numbers since because of tail recursion
  def anotherFactorial(number: Int): BigInt = {
    @tailrec
    def factorialHelper(x: Int, accumulator: BigInt): BigInt = {
      if (x <= 1) accumulator
      else factorialHelper(x - 1, x * accumulator) // Tail Recursion = use a recursive call as the last expression on each code path
    }
    factorialHelper(number, 1)
  }

  println(anotherFactorial(5000))

  // When you need loops, use tail recursion

  // Accumulators must be of the same type of the function return type
  @tailrec
  def concatenateTailRec(aString: String, n: Int, accumulator: String): String = {
    if (n <= 0) accumulator
    else concatenateTailRec(aString, n - 1, aString + accumulator)
  }

  println(concatenateTailRec("Hello", 6, ""))

  def isPrime(n: Int): Boolean = {
    @tailrec
    def isPrimeTailRec(t: Int, isStillPrime: Boolean): Boolean = {
      if (!isStillPrime) false
      else if (t <= 1) true
      else isPrimeTailRec(t - 1, n % t != 0 && isStillPrime)
    }
    isPrimeTailRec(n / 2, true)
  }

  println(isPrime(2003))
  println(isPrime(629))

  // However many recursive calls you have on the same code path that's how many
  // accumulators you need to have in the tail recursive function

  def fibonacci(n: Int): Int = {
    @tailrec
    def fibonacciTailRec(i: Int, last: Int, nextToLast: Int): Int = {
      if (i >= n) last
      else fibonacciTailRec(i + 1, last + nextToLast, last)
    }
    if (n <= 2) 1
    else fibonacciTailRec(2, 1,1)
  }

  println(fibonacci(8))

}
