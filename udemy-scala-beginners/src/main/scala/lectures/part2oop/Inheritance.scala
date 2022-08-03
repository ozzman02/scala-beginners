package lectures.part2oop

object Inheritance extends App {

  // Single class inheritance
  // Access modifiers are protected, private and public
  class Animal {
    val creatureType = "wild"
    //def eat = println("animal")
    //private def eat = println("animal")
    protected def eat = println("Animal eating")
    def talk = println("Animal talking")
    final def walk = println("Animal Walking")
  }

  class Cat extends Animal {
    def crunch = {
      eat
      println("cat crunch crunch")
    }
  }

  // Final class, no class can extend from this one
  final class SuperAnimal {}

  // Sealed class
  sealed class MegaAnimal {}


  val cat = new Cat
  cat.crunch

  // Constructors
  class Person(name: String, age: Int) {

    // Auxiliary constructor
    def this(name: String) = this(name, 0)
  }

  /* class Adult(name: String, age: Int, idCard: String) extends Person does
      not compile because we do not have a no args constructor in class Person. Need to pass name and age.

      class Adult(name: String, age: Int, idCard: String) extends Person(name) is valid because the compiler founds
      the auxiliary constructor.
      */
  class Adult(name: String, age: Int, idCard: String) extends Person(name, age)

  // Overriding
  class Dog extends Animal {

    override val creatureType: String = "domestic"

    override def eat: Unit = {
      super.eat // call to parent constructor
      println("dog crunch crunch")
    }

  }

  class Dog2(override val creatureType: String) extends Animal {
    override def talk: Unit = println("dog2 talking")
  }

  class Dog3(dogType: String) extends Animal {
    override val creatureType: String = dogType
  }

  val dog = new Dog
  dog.eat
  println(dog.creatureType)

  val dog2 = new Dog2("K9")
  println(dog2.creatureType)

  val dog3 = new Dog3("Bulldog")
  println(dog3.creatureType)

  // Type Substitution (broad polymorphism)
  // unknown animal is an instance of Dog2 therefore it will use Dog's2 method implementation for talk
  // A method call will go to the most overridden version
  val unknownAnimal: Animal = new Dog2("K9")
  unknownAnimal.talk

  /*
      Preventing overrides

        1.  Using final keyword on member
        2.  Using final on the class
        3.  Seal the class: Can extend classes in this file but prevents extension in other files
   */

}
