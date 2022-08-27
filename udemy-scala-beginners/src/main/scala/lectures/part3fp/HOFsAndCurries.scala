package lectures.part3fp

object HOFsAndCurries extends App {

  // How do we read this function?
  // Return type: (Int => Int)
  // Parameters:
  //  1- Int
  //  2- (String, (Int => Boolean)) => Int
  val superFunction: (Int, (String, (Int => Boolean)) => Int) => (Int => Int) = null

  // Function that applies a function n times over a value x
  // nTimes(f, n, x)
  // nTimes(f, 3, x) = f(f(f(x))) = nTimes(f, 2, f(x)) = f(f(f(x)))
  // nTimes(f, n, x) = f(f(...f(x))) = nTimes(f, n-1, f(x))
  def nTimes(f: Int => Int, n: Int, x: Int): Int = {
    if (n <= 0) x
    else nTimes(f, n-1, f(x))
  }

  val plusOne = (x: Int) => x + 1
  println(nTimes(plusOne, 10, 1))

  def nTimesImproved(f: Int => Int, n: Int): Int => Int = {
    if (n <= 0) (x: Int) => x
    else (x: Int) => nTimesImproved(f, n-1)(f(x))
  }

  val plus10 = nTimesImproved(plusOne, 10)
  println(plus10(1))

  // Curried Functions
  // Function receives an Int and return a Function
  val superAdder: Int => (Int => Int) = (x: Int) => (y: Int) => x + y
  val add3 = superAdder(3) // y => 3 + y
  println(add3(10)) // prints 13
  println(superAdder(3)(10))

  // Curried Functions with multiple parameter lists
  def curriedFormatter(c: String)(x: Double): String = c.format(x)

  // Need to define type for smaller functions otherwise the code will not compile
  val standardFormat: (Double => String) = curriedFormatter("%4.2f")
  val preciseFormat: (Double => String) = curriedFormatter("%10.8f")

  println(standardFormat(Math.PI))
  println(preciseFormat(Math.PI))

  // Exercises
  def toCurry(f: (Int, Int) => Int): (Int => Int => Int) = {
    // a lambda taking a value x which returns a lambda taking a value y and the result is going to be f(x, y)
    x => y => f(x, y)
  }

  def fromCurry(f: (Int => Int => Int)): (Int, Int) => Int = {
    // a lambda with two arguments
    (x, y) => f(x)(y)
  }

  def compose[A,B,T](f: A => B, g: T => A): T => B = {
    // f apply to g apply to x
    x => f(g(x))
  }

  def andThen[A,B,C](f: A => B, g: B => C): A => C = {
    // g apply to f apply to x
    x => g(f(x))
  }

  def superAdder2: (Int => Int => Int) = toCurry(_ + _)
  def add4 = superAdder2(4)
  println(add4(17))

  val simpleAdder = fromCurry(superAdder)
  println(simpleAdder(4, 17))

  val add2 = (x: Int) => x + 2
  val times3 = (x: Int) => x * 3

  val composed = compose(add2, times3) // applies times3 first
  val ordered = andThen(add2, times3)

  println(composed(4))
  println(ordered(4))

}
