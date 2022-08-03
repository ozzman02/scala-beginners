package lectures.part2oop

object Exceptions extends App {

  val x: String = null
  // println(x.length) will crash the program with a NullPointerException

  // 1. Throwing Exceptions

  // this one returns Nothing
  //val aWeirdValue = throw new NullPointerException

  // this one returns String, also valid
  //val anotherWeirdValue:String = throw new NullPointerException

  // Throwable classes extend the Throwable class
  // Exception and Error are the major Throwable subtypes

  // 2. How to catch Exceptions
  def getInt(withExceptions: Boolean): Int = {
    if (withExceptions) throw new RuntimeException("No int for you!")
    else 42
  }

  try {
    // code that might fail
    val num = getInt(true)
    println(num)
  } catch {
    case e: RuntimeException => println("caught a Runtime exception")
  } finally {
    // code that will get executed no matter what
    // optional
    // does not influence the return type of the expression
    // use finally only for side effects
    println("finally")
  }

  // potentialFail is of type Int since getInt and the catch block returns an int
  val potentialFail = try {
    getInt(true)
  } catch {
    case e: RuntimeException => 43
  } finally {
    println("finally")
  }

  println(potentialFail)

  // 3. Define your own Exceptions
  class MyException extends Exception

  val exception = new MyException

  // Throw it
  // throw exception

  // val array = Array.ofDim[Int](Int.MaxValue)

  /* def infinite: Int = 1 + infinite
  val noLimit = infinite */

  class OverflowException extends RuntimeException
  class UnderflowException extends RuntimeException
  class MathCalculationException extends RuntimeException("Division by 0")

  object PocketCalculator {

    def add(x: Int, y: Int): Int = {
      val result = x + y
      if (x > 0 && y > 0) throw new OverflowException
      else if (x < 0 && y < 0 && result > 0) throw new UnderflowException
      else result
    }

    def subtract(x: Int, y: Int): Int = {
      val result = x - y
      if (x > 0 && y < 0 && result < 0) throw new OverflowException
      else if (x < 0 && y > 0 && result > 0) throw new UnderflowException
      else result
    }

    def multiply(x: Int, y: Int): Int = {
      val result = x * y
      if (x > 0 && y > 0 && result < 0) throw new OverflowException
      else if (x < 0 && y < 0 && result < 0) throw new OverflowException
      else if (x > 0 && y < 0 && result > 0) throw new UnderflowException
      else if (x < 0 && y > 0 && result > 0) throw new UnderflowException
      else result
    }

    def divide(x: Int, y: Int): Int = {
      if (y == 0) throw new MathCalculationException
      else x / y
    }

  }

  //println(PocketCalculator.add(Int.MaxValue, 10))
  println(PocketCalculator.divide(2,0))
}
