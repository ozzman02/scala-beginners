package lectures.part2oop

object Generics extends App {

  class AnotherList[+A] {
    /*
        A = Cat
        B = Animal

        If we add an animal to a list of cats it will turn into a list of animals
    */
    def add[B >: A](element: B): MyList[B] = ???
  }

  class MyList[A] {
    // Use the type A
  }

  // Companion definition
  object MyList {

    // Generic method
    def empty[A]: MyList[A] = ???

  }

  class MyMap[Key, Value] {

  }

  trait MyTrait[A] {

  }

  val listOfIntegers = new MyList[Int]
  val listOfStrings = new MyList[String]

  val emptyListOfIntegers = MyList.empty[Int]

  // Variance problem
  class Animal
  class Cat extends Animal
  class Dog extends Animal

  /*
      Does a list of Cat also extends a list of Animal?

      1. Yes, list of Cat extends list of Animal = Covariance
   */
  class CovariantList[+A]
  val animal: Animal = new Cat
  val animalList: CovariantList[Animal] = new CovariantList[Cat]

  /*
      Can I add any Animal to the animalList? animalList.add(new Dog) ? Hard Question...
      We need to return a list of animals with something like this:

        A = Cat
        B = Animal

          def add[B >: A](element: B): MyList[B] = ???

      2. NO = Invariance

          class InvariantList[A]
          // can't be done
          val invariantAnimalList: InvariantList[Animal] = new InvariantList[Cat]
   */
  class InvariantList[A]
  val invariantAnimalList: InvariantList[Animal] = new InvariantList[Animal]

  /*
      3. Hell no ! Contravariance
  */
  class ContravariantList[-A]
  val contravariantList: ContravariantList[Cat] = new ContravariantList[Animal]

  // Contravariance semantics looks better. An animal trainer is better because it can train dogs, cats, etc
  class Trainer[-A]
  val trainer: Trainer[Cat] = new Trainer[Animal]


  /*
      Bounded Types

        Class Cage only accepts type parameters A that are subclasses of Animal
          class Cage[A <: Animal] (animal: A)

        Class Cage only accepts type parameters A that are superclasses of Animal
          class Cage[A >: Animal] (animal: A)

        This will throw an error:

          class Car
          val newCage = new Cage(new Car)
   */

  class Cage[A <: Animal] (animal: A)
  val cage = new Cage(new Dog)


}
