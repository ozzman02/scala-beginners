package lectures.part3fp

object AnonymousFunctions extends App {

  /*
    val doubler = new Function1[Int, Int] {
      override def apply(x: Int): Int = x * 2
    }

    Equivalent expression

      This is an anonymous function or lambda

        val doubler = (x: Int) => x * 2

          or

        val doubler: Int => Int = x => x * 2

  */
  val doubler = (x: Int) => x * 2

  /*
    Multiple params in a lambda

      val adder = (a: Int, b: Int) => a + b

        or

      val adder: (Int, Int) => Int = (a, b) => a + b
  */
  val adder = (a: Int, b: Int) => a + b

  /*
      No params

        val justDoSomething = () => 3
        val justDoSomething: () => Int = () => 3
  */
  val justDoSomething = () => 3

  println(justDoSomething)      // function itself, prints lectures.part3fp.AnonymousFunctions$$$Lambda$17/0x00000008000c0c40@4eb7f003
  println(justDoSomething())    // actual call, prints 3

  /*
      Curly braces with Lambdas
  */
  val stringToInt = {
    (str: String) => str.toInt
  }

  /*
      More syntactic sugar

        val niceIncrementer: Int => Int = (x: Int) => x + 1

        val niceIncrementer: Int => Int = _ + 1

            _ + 1 is equivalent to x => x + 1

        val niceAdder: (Int, Int) => Int = (a, b) => a + b

        val niceAdder: (Int, Int) => Int = _ + _

            _ + _ is equivalent to (a, b) => a + b

        To use _ notation need provide the function type to the signature
  */
  val niceIncrementer: Int => Int = _ + 1

  val niceAdder: (Int, Int) => Int = _ + _

  /*
    val superAdder: (Int) => (Int => Int) = new Function1[Int, Function1[Int, Int]] {
      override def apply(x: Int): (Int) => Int = new Function1[Int, Int] {
        override def apply(y: Int): Int = x + y
      }
    }
  */
  val superAdd = (x: Int) => (y: Int) => x + y
  println(superAdd(3)(4))

}
