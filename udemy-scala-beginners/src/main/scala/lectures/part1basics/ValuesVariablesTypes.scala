package lectures.part1basics

object ValuesVariablesTypes extends App {

  val x: Int = 42
  val y = 42
  println(x)
  println(y)

  // vals are immutable
  // compiler can infer types
  // ; are optional but needed when multiple expressions are written in the same line

  val aString: String = "hello"
  val anotherString = "goodbye"

  val aBoolean: Boolean = false
  val aChar: Char = 'a'
  val anInt: Int = x
  val aShort: Short = 4613
  val aLong: Long = 527398527777L
  val aFloat: Float = 2.0f
  val aDouble: Double = 3.14

  // Variables
  var aVariable: Int = 4
  aVariable = 5 // side effects

}
