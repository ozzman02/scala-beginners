package lectures.part2oop

object AbstractDataTypes extends App {

  abstract class Animal {
    val creatureType: String = "wild"
    def eat: Unit
  }

  class Dog extends Animal {
    override val creatureType: String = "Canine"
    // override keyword is not mandatory
    def eat: Unit = println("dog crunch crunch")
  }

  // Traits
  trait Carnivore {
    val preferredMeal: String = "fresh meat"
    def eat(animal: Animal): Unit
  }
  trait ColdBlooded {

  }
  class Crocodile extends Animal with Carnivore with ColdBlooded {
    override val creatureType: String = "crocodile"
    def eat: Unit = println("crocodile just eating")
    def eat(animal: Animal): Unit = println(s"I'm a crocodile and I'm eating ${animal.creatureType}")
  }

  val dog = new Dog
  val crocodile = new Crocodile
  crocodile.eat(dog)

  /*
      Abstract vs Traits

        Abstract classes can have both abstract and none abstract members so can traits.

        1. Traits do not have constructor parameters
        2. Multiple traits may be inherited by the same class
        3. Traits are behavior, Abstract classes are more like "a thing"
  */
}
