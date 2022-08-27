package lectures.part4pm

object Interesting extends App {

  // Interesting ...
  val numbers = List(1, 2, 3, 4)
  val numbersMatch = numbers match {
    case listOfStrings: List[String] => "a list of strings"
    case listOfNumbers: List[Int] => "a list of numbers"
    case _ => ""
  }

  // This will print "a list of strings"
  // JVM trick question, it does type erasure
  println(numbersMatch)

}
