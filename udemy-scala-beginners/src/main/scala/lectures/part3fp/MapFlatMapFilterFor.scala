package lectures.part3fp

object MapFlatMapFilterFor extends App {

  val list = List(1, 2, 3, 4)
  println(list)
  println(list.head)
  println(list.tail)

  // map
  println(list.map(_ + 1))
  println(list.map(_ + " is a number"))

  // filter
  println(list.filter(_ % 2 == 0))

  // flatMap
  val toPair = (x: Int) => List(x, x + 1)
  println(list.flatMap(toPair))

  // Print all combinations between two lists
  // Note: If you have two loops you do a map and a flatMap
  // Note: If you have three loops you do two flatMaps and a map at the end
  val numbers = List(1,2,3,4,5)
  val chars = List('a','b','c','d','e')
  val colors = List("black", "white")

  // two loops -> "iterations"
  val combinationsBetweenNumbersAndChars = numbers.flatMap(n => chars.map(c => "" + c + n))

  // three loops -> "iterations"
  val combinationsBetweenNumbersCharsAndColors =
    numbers.flatMap(number => chars.flatMap(character => colors.map(color => "" + character + number + "-" + color)))

  val filteredCombinationsBetweenNumbersCharsAndColors =
    numbers.filter(_ % 2 == 0)
      .flatMap(number => chars
        .flatMap(character => colors.map(color => "" + character + number + "-" + color)
        )
      )

  println(combinationsBetweenNumbersAndChars)
  println(combinationsBetweenNumbersCharsAndColors)

  // foreach
  list.foreach(println)

  // for-comprehensions
  val forCombinations = for {
    n <- numbers if n % 2 == 0
    c <- chars
    color <- colors
  } yield "" + c + n + "-" + color

  println(forCombinations)
  println(filteredCombinationsBetweenNumbersCharsAndColors)

  // equivalent of numbers.foreach(println)
  for {
    n <- numbers
  } println(n)

  // syntax overload
  list.map {
    x => x * 2
  }

  // Exercises


}
