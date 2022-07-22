package lectures.part2oop

import scala.annotation.targetName
import scala.language.postfixOps

// infix, prefix and postfix notations
object MethodNotations extends App {

  class Person(val name: String, favoriteMovie: String) {
    def likes(movie: String): Boolean = {
      movie == favoriteMovie
    }
    def hangOutWith(person: Person): String = {
      s"${this.name} is hanging out with ${person.name}"
    }
    def +(person: Person): String = {
      s"${this.name} is hanging out with ${person.name}"
    }
    def +(nickName: String): Person = {
      new Person(s"$name ($nickName)", favoriteMovie)
    }
    def unary_! : String = s"What the heck?!"
    def isAlive: Boolean = true
    def apply(): String = s"Hi, my name is $name and I like $favoriteMovie"
  }

  val mary: Person = new Person("Mary", "Inception")
  println(mary.likes("Inception"))
  println(mary likes "Inception") // infix notation - operator notation (syntactic sugars)

  // operators in Scala

  val tom: Person = new Person("Tom", "Fight Club")

  println(mary hangOutWith tom)
  println(mary + tom)
  println(mary.+(tom))

  // All Operators are methods
  println(1 + 2)
  println(1.+(2))

  // Prefix notation
  val x = -1  // equivalent with 1.unary_-
  val y = 1.unary_-

  // unary_ prefix only works with - + ~ !
  println(!mary)
  println(mary.unary_!)

  // Postfix notation
  // Only available with methods that have no arguments
  println(mary.isAlive)
  println(mary isAlive)

  // Apply
  println(mary.apply())
  println(mary()) // equivalent to method apply

  // Exercises
  println((mary + "The Rockstar")())
  println((mary + "The Rockstar").apply())
}
