package lectures.part1basics

import scala.annotation.tailrec

object Functions extends App {

  def aFunction(a: String, b: Int): String = {
    a + " " + b
  }

  // calling a function is also an expression
  println(aFunction("hello", 3))

  def aParameterLessFunction(): Int = 42

  println(aParameterLessFunction())

  def aRepeatedFunction(aString: String, n: Int): String = {
    if (n == 1) aString
    else aString + aRepeatedFunction(aString, n - 1)
  }

  println(aRepeatedFunction("Hello", 3))

  // When you need loops use recursion
  // As best practice always use return type for recursive function because the compiler can't infer the return type

  def aFunctionWithSideEffects(aString: String): Unit = println(aString)

  def aBigFunction(n: Int): Int = {
    def aSmallerFunction(a: Int, b: Int): Int = a + b
    aSmallerFunction(n, n - 1) // returned expression
  }

  //Greeting function (name, age) => "Hi, my name is $name and I am $age years old."
  def aGreeting(name: String, age: Int): Unit = {
    println(s"Hi, my name is $name and I am $age years old.")
  }

  aGreeting("Oscar", 40)

  def aFactorial(number: Int):Int = {
    if (number <= 1) 1
    else number * aFactorial(number - 1)
  }

  println(aFactorial(5))

  def anotherFibonacci(number: Int): Int = {
    if (number <= 2) 1 else anotherFibonacci(number - 1) + anotherFibonacci(number - 2)
  }

  println(anotherFibonacci(8));

  @tailrec
  def isPrime(number: Int, i: Int): Boolean = {
    if (number <= 2) {
      if (number == 2) true else false
    } else if (number % i == 0) {
      false
    } else if (Math.sqrt(i) > number) {
      true
    } else {
      isPrime(number, i + 2)
    }
  }

  println(isPrime(29, 2))

  def isPrime(number: Int): Boolean = {
    @tailrec
    def isPrimeUntil(t: Int): Boolean = {
      if (t <= 1) true else number % t !=0 && isPrimeUntil(t - 1)
    }
    isPrimeUntil(number / 2)
  }

  println(isPrime(37))
}
