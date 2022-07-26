package lectures.part2oop

object CaseClasses extends App {

  /* equals, hashCode, toString */
  case class Person(name: String, age: Int) {

  }

  // 1. class parameters are fields
  val jim = new Person("Jim", 34)
  println(jim.name)

  // 2. sensible toString
  // It prints Person(Jim,34) but without the case keyword it prints lectures.part2oop.CaseClasses$Person@27f723
  // println(instance) = println(instance.toString)
  println(jim.toString)
  println(jim)

  // 3. equals and hashCode implemented out of the box
  // println(jim == jim2) is true with case classes otherwise comparison will be false
  val jim2 = new Person("Jim", 34)
  println(jim == jim2)

  // 4. Case classes have handy copy method
  val jim3 = jim.copy(age = 45)
  println(jim3)

  // 5. Case classes have companion objects
  val thePerson = Person
  val mary = Person("Mary", 23)

  // 6. Case classes are serializable
  // Akka

  // 7. Case classes have extracted patterns. Case classes can be used in pattern matching

  // Case Objects acts like case classes but they are objects.
  // Case Objects have the same properties as Case Classes except they don't get companion objects because they are their own companion objects.
  case object UnitedKingdom {
    def name: String = "The UK of GB and NI"
  }


  


}
