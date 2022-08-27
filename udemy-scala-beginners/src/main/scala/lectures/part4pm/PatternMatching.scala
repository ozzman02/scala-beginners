package lectures.part4pm

import scala.util.Random

object PatternMatching extends App {

  val random = new Random
  val x = random.nextInt(10)

  /*
    1. Cases are matched in order
    2. If no cases matched? MatchError
    3. Type of the Pattern Match expression? Unified type of all the types in all the cases
    4. Works really well with case classes
  */
  val description = x match {
    case 1 => "the One"
    case 2 => "double or nothing"
    case 3 => "third time is the charm"
    case _ => "something else ..." // _ = wildcard
  }

  println(x)
  println(description)

  // 1. Decompose values. Case classes have the ability to be deconstructed or extracted in pattern matching
  case class Person(name: String, age: Int)

  val bob = Person("Bob", 20)

  val greeting = bob match {
    case Person(n, a) => s"Hi my name is $n and I can't drink in the US"
    case Person(n, a) if a < 21 => s"Hi my name is $n and I am $a years old"
    case _ => "I do not know who I am"
  }

  println(greeting)


  // 2. Pattern Matching on sealed hierarchies
  sealed class Animal
  case class Dog(breed: String) extends Animal
  case class Parrot(greeting: String) extends Animal

  val animal = Dog("Terra Nova")
  animal match {
    case Dog(someBreed) => println(s"Matched a dog of the $someBreed breed")
  }

  // Do not match everything
  // Why?
  val isEven = x match {
    case n if n % 2 == 0 => true
    case _ => false
  }
  val isEvenCond = if (x % 2 == 0) true else false
  val isEvenNormal = x % 2 == 0

  /*
    Exercise

      Simple function uses PM takes an Expr => human readable form

        Sum(Number(2), Number(3)) => 2 + 3
        Sum(Sum(Number(2), Number(3)), Number(4)) => 2 + 3 + 4
        Prod(Sum(Number(2), Number(1)), Number(3)) => (2+1) * 3
        Sum(Prod(Number(2), Number(1)), Number(3)) => 2 * 1 + 3
  */
  trait Expr
  case class Number(n: Int) extends Expr
  case class Sum(e1: Expr, e2: Expr) extends Expr
  case class Prod(e1: Expr, e2: Expr) extends Expr

  def show(e: Expr): String = e match {
    case Number(n) => s"$n"
    case Sum(e1, e2) => show(e1) + " + " + show(e2)
    case Prod(e1, e2) =>
      def maybeShowParentheses(expr: Expr) = expr match {
        case Prod(_, _) => show(expr)
        case Number(_) => show(expr)
        case _ => "(" + show(expr) + ")"
      }
      maybeShowParentheses(e1) + " * " + maybeShowParentheses(e2)
  }

  println(show(Sum(Number(2), Number(3))))
  println(show(Sum(Sum(Number(2), Number(3)), Number(4))))
  println(show(Prod(Sum(Number(2), Number(1)), Number(3))))
  println(show(Sum(Prod(Number(2), Number(1)), Number(3))))

  println(show(Prod(Sum(Number(2), Number(1)), Sum(Number(3), Number(4)))))

}
