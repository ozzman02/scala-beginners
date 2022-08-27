package lectures.part3fp

import scala.util.Random

object Sequences extends App {

  // Seq
  val aSequence = Seq(1,3,2,4)
  println(aSequence)
  println(aSequence.reverse)
  println(aSequence(2))
  println(aSequence ++ Seq(7,5,6))
  println(aSequence.sorted)

   // Ranges
   val aRange: Seq[Int] = 1 until 10
   aRange.foreach(println)

  (1 to 10).foreach(x => println("Hello"))

  // List
  // +: prepend, :+ append
  val aList = List(1,2,3)
  println(aList) // prints List(1, 2, 3)

  val prepended = 42 :: aList
  println(prepended) // prints List(42, 1, 2, 3)

  val prependedAndAppended = 42 +: aList :+ 89
  println(prependedAndAppended) // prints List(42, 1, 2, 3, 89)

  val apple5 = List.fill(5)("apple")
  println(apple5) // prints List(apple, apple, apple, apple, apple)

  println(aList.mkString("-!-")) // prints 1-!-2-!-3

  // Arrays
  val numbers = Array(1,2,3,4)
  println(numbers) // prints [I@4c762604

  val threeElementsArray = Array.ofDim[Int](3)
  println(threeElementsArray) // prints [I@df27fae

  threeElementsArray.foreach(println)

  // mutation
  numbers(2) = 0 // syntax sugar for numbers.update(2, 0)
  println(numbers.mkString(" "))

  // Arrays and Sequences
  val numbersSeq: Seq[Int] = numbers // implicit conversion
  println(numbersSeq) // prints ArraySeq(1, 2, 0, 4)

  // Vectors
  val vector: Vector[Int] = Vector(1,2,3)
  println(vector)

  // Vectors vs List
  val maxRuns = 1000
  val maxCapacity = 1000000

  def getWriteTime(collection: Seq[Int]): Double = {
    val r = new Random
    val times = for {
      it <- 1 to maxRuns
    } yield {
      val currentTime = System.nanoTime()
      collection.updated(r.nextInt(maxCapacity), r.nextInt())
      System.nanoTime() - currentTime
    }
    times.sum * 1.0 / maxRuns
  }

  // Advantage -> keeps a reference to tail
  // Disadvantage -> updating element in the middle takes a long time
  val numbersList = (1 to maxCapacity).toList // prints 5992093.551 nanoseconds -> 5.992093551 milliseconds

  // Advantage -> depth of the tree is small
  // Disadvantage -> needs to replace an entire 32-element chunck
  val numbersVector = (1 to maxCapacity).toVector // prints 10460.546 ns -> 0.010460546 ms

  println(getWriteTime(numbersList))
  println(getWriteTime(numbersVector))

}
