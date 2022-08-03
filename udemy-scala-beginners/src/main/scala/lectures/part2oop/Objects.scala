package lectures.part2oop

object Objects extends App {

  /*
    In Java

      public class JavaPlayground {
        public static void main (String[] args) {
          System.out.println(Person.N_EYES)
        }
      }
      class Person {
        public static final int N_EYES = 2
      }
  */

  // Scala does not have class level functionality ("static")
  /* The pattern of writing classes and objects with the same name in the same scope
      is called Companions. Object Person and class Person are companions */
  // Similar to "static/class" level functionality
  object Person {

    val N_EYES = 2

    def canFly: Boolean = false

    // factory method
    def apply(mother: Person, father: Person): Person = new Person("Bobbie")
  }

  // instance level functionality
  class Person (val name: String) {
  }

  println(Person.N_EYES)
  println(Person.canFly)

  // Scala object = Singleton instance. These vals are Object Person types.
  // The result of this comparison will be true.
  val mary = Person
  val john = Person
  println(mary == john)

  // These vals are class Person types. The result of this comparison will be false.
  val oscar = new Person("Oscar")
  val stacy = new Person("Stacy")
  println(oscar == stacy)

  val bobbie = Person.apply(oscar, stacy)
  // this is a reference to the apply method already defined
  val julia = Person(oscar, stacy)

  // Scala Applications
  // Is a Scala object with a method def main(args: Array[String]): Unit
  /*
    object Objects {

      object Person {
        val N_EYES = 2
        def canFly: Boolean = false
        def apply(mother: Person, father: Person): Person = new Person("Bobbie")
      }

      class Person (val name: String) {
      }

      def main(args: Array[String]): Unit = {

        println(Person.N_EYES)
        println(Person.canFly)

        val mary = Person
        val john = Person
        println(mary == john)

        val oscar = new Person("Oscar")
        val stacy = new Person("Stacy")
        println(oscar == stacy)

        val bobbie = Person.apply(oscar, stacy)
        val julia = Person(oscar, stacy)
      }
    }
  */

}
