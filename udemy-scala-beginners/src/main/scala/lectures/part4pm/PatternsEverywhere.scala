package lectures.part4pm

object PatternsEverywhere extends App {

  // Big idea #1
  // Catches are actually matches
  try {
    // code
  } catch {
    case e: RuntimeException => "runtime"
    case npe: NullPointerException => "NPE"
    case _ => "something else"
  }

  // Big idea #2
  val list = List(1, 2, 3, 4)
  val evenOnes = for {
    x <- list if x % 2 == 0 // generators are also based on Pattern Matching
  } yield (10 * x)

  val tuples = List((1, 2), (3, 4))
  val filteredTuples = for {
    (first, second) <- tuples // generators are also based on Pattern Matching
  } yield first * second

  // Big idea #3
  // Multiple value definitions based on Pattern Matching, not only limited to tuples
  val tuple = (1,2,3)
  val (a, b, c) = tuple
  println(2)

  val head :: tail = list
  println(head)
  println(tail)

  // Big idea #4
  // Partial function literal based on Pattern Matching
  val mappedList = list.map {
    case v if v % 2 == 0 => v + " is even"
    case 1 => "the one"
    case _ => "something else"
  }

  // Partial fuction literal above is equivalent to this one
  val mappedList2 = list.map {
    x => x match {
      case v if v % 2 == 0 => v + " is even"
      case 1 => "the one"
      case _ => "something else"
   }
  }

  println(mappedList)

}
