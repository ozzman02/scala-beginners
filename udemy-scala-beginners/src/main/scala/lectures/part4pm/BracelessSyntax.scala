package lectures.part4pm

object BracelessSyntax {

  // If expressions
  val anIfExpression = if (2 > 3) "bigger" else "smaller"

  // java style
  val anIfExpressionV2 =
    if (2 > 3) {
      "bigger"
    } else {
      "smaller"
    }

  // compact
  val anIfExpressionV3 =
    if (2 > 3) "bigger"
    else "smaller"

  // scala 3
  val anIfExpressionV4 =
    if 2 > 3 then
      "bigger"
    else
      "smaller"

  val anIfExpressionV5 =
    if 2 > 3 then
      val result = "bigger"
      result
    else
      val result = "smaller"
      result

  // scala 3 one-liner
  val anIfExpressionV6 = if 2 > 3 then "bigger" else "smaller"

  // For-comprehensions
  val aForComprehension = for {
    n <- List(1, 2, 3)
    s <- List("black", "white")
  } yield s"$n$s"

  // scala 3
  val aForComprehensionV2 =
    for
      n <- List(1, 2, 3)
      s <- List("black", "white")
    yield s"$n$s"

  // Pattern Matching
  val meaningOfLife = 42
  val aPatternMatching = meaningOfLife match {
    case 1 => "the one"
    case 2 => "double or nothing"
    case _ => "something else"
  }

  // scala 3
  val aPatternMatchingV2 = meaningOfLife match
    case 1 => "the one"
    case 2 => "double or nothing"
    case _ => "something else"

  // methods without braces
  def computeMeaningOfLife(arg: Int): Int = {
    val partialResult = 40
    partialResult + 2
  }

  def computeMeaningOfLifeV2(arg: Int): Int =
    val partialResult = 40
    partialResult + 2

  // class definition with significant indentation (same for traits, objects, enums, etc)
  class Animal {
    def eat(): Unit = println("I'm eating")
  }

  class AnimalV2:

    def eat(): Unit =
      println("I'm eating")
    end eat

    def grow(): Unit = "I'm growing"

    // ... more lines of code ...

  end AnimalV2 // end token is valid for if, match, for, method, classes, traits, enums, objects

  // anonymous classes
  val aSpecialAnimal = new Animal:
    override def eat(): Unit = println("I'm special")


  def main(args: Array[String]): Unit = {
    println(anIfExpressionV5)
  }

}
