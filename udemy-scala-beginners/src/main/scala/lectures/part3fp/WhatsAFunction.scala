package lectures.part3fp

object WhatsAFunction extends App {

  // Dream: use functions as first class elements
  // Problem: OOP

  val doubler = new MyFunction[Int, Int] {
    override def apply(element: Int): Int = element * 2
  }

  // call to the apply method
  println(doubler(2))

  // function types = Function[A, B]
  val stringToIntConverter = new Function1[String, Int] {
    override def apply(string: String): Int = string.toInt
  }

  println(stringToIntConverter("3") + 4)

  /*
    This function can also be written like this:

        val adder = new ((Int, Int) => Int) {
          override def apply(a: Int, b: Int): Int = a + b
        }

        OR

        val adder:(Int, Int) => Int = new Function2[Int, Int, Int] {
          override def apply(a: Int, b: Int): Int = a + b
        }
  */
  val adder = new Function2[Int, Int, Int] {
    override def apply(a: Int, b: Int): Int = a + b
  }

  println(adder(2,2))

  // Function Types Sugar --> Function2[A, B, R] === (A, B) => R
  // Example: Function2[Int, String, Int] === (Int, String) => Int
  // All Scala functions are objects. Function traits, up to 22 params

  // Exercises

  val strJoiner = new ((String, String) => String) {
    override def apply(string1: String, string2: String): String = {
      string1.concat(string2)
    }
  }

  println(strJoiner("Hello ", "World"))

  val secondIntFunction = new (Int => Int) {
    override def apply(number: Int): Int = number + 1
  }

  val firstIntFunction = new (Int => Int) {
    override def apply(number: Int): Int = secondIntFunction(number)
  }

  println(firstIntFunction(1))

  /*val superAdder: Function1[Int, Function1[Int, Int]] = new Function1[Int, Function1[Int, Int]] {
    override def apply(x: Int): Function1[Int,Int] = new Function1[Int, Int] {
      override def apply(y: Int): Int = x + y
    }
  }*/

  val superAdder: (Int) => (Int => Int) = new Function1[Int, Function1[Int, Int]] {
    override def apply(x: Int): (Int) => Int = new Function1[Int, Int] {
      override def apply(y: Int): Int = x + y
    }
  }

  /* supperAdder is a curried function */
  val superAdderCaller = superAdder(3)(4)
  println(superAdderCaller)

  /*
      This also prints 7

      val adder3 = superAdder(3)
      println(adder3(4))
  */
}

/*
  OOP Approach

    trait Action[A, B] {
      def execute(element: A): B
    }
*/
trait MyFunction[A, B] {
  def apply(element: A): B
}

