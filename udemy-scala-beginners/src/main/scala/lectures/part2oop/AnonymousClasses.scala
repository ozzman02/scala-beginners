package lectures.part2oop

/*
      Anonymous class

        - We can instantiate types and override fields or methods on the spot

        - Works for traits and classes (abstract or not)

        - Rules
          - Pass in required constructor args if needed
          - Implement all abstract fields/methods

        Example:

          abstract class Animal {
            def eat: Unit
          }

          val funnyAnimal: Animal = new Animal {
            override def eat: Unit = println("jajajajajaj")
          }

          The compiler does:

            class AnonymousClasses$$anon$1 extends Animal {
              override def eat: Unit = println("jajajajajaj")
            }
            val funnyAnimal: Animal = new AnonymousClasses$$anon$1
   */
object AnonymousClasses extends App {

  abstract class Animal {
    def eat: Unit
  }

  val funnyAnimal: Animal = new Animal {
    override def eat: Unit = println("jajajajajaj")
  }

  println(funnyAnimal.getClass)

  class Person(name: String) {
    def sayHi(): Unit = println(s"Hi, my name is $name, how can I help?")
  }

  val jim = new Person("Jim") {
    override def sayHi(): Unit = println(s"Hi, my name is Jim, how can I be of service?")
  }

}
