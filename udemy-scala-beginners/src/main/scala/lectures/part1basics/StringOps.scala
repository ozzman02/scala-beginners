package lectures.part1basics

object StringOps extends App {

  val str: String = "Hello, I am learning Scala"

  println(str.charAt(2))
  println(str.substring(7, 11)) // first index is inclusive, last index is exclusive
  println(str.split(" ").toList)
  println(str.startsWith("Hello"))
  println(str.replace(" ", "-"))
  println(str.toLowerCase())
  println(str.length)

  val aNumberString = "45"
  val aNumber = aNumberString.toInt
  println('a' +: aNumberString :+ 'z')

  println(str.reverse)
  println(str.take(2))

  // Scala-specific: String interpolators
  // S-Interpolator
  val name = "David"
  val age = 12
  val greeting = s"Hello, my name is $name and I am $age years old"
  val anotherGreeting = s"Hello, my name is $name and I will be turning ${age + 1} years old."
  println(greeting)
  println(anotherGreeting)

  // F-Interpolators
  val speed = 1.2f
  val myth = f"$name%s can eat $speed%2.2f" // 2 characters total minimum and 2 decimals of precision
  println(myth)

  // Raw-Interpolators
  println(raw"This is a \n new line") // Back slashes will not be escaped

  val escaped = "This is a \n new line"
  println(raw"$escaped") // Back slashes will be escaped

}
