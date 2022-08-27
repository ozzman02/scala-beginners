package lectures.part3fp

import scala.annotation.tailrec

object TuplesAndMaps extends App {

  // Tuples = finite ordered "lists"
  val aTuple = new Tuple2(2, "hello, Scala") // Tuple2[Int, String] = (Int, String)
  val anotherTuple = (2, "hello, Scala")

  println(aTuple._1) // prints 2
  println(aTuple.copy(_2 = "goodbye Java")) // prints (2,goodbye Java)
  println(aTuple.swap) // prints (hello, Scala,2)

  // Maps - keys -> values
  val aMap: Map[String, Int] = Map() // Empty Map

  // Method withDefaultValue is helpful to prevent exception if a key does not exist
  val phoneBook = Map(("JIM", 555), "DANIEL" -> 789).withDefaultValue(-1)
  println(phoneBook) // prints Map(Jim -> 555, Daniel -> 789)

  println(phoneBook.contains("JIM")) // prints true
  println(phoneBook("JIM")) // prints 555

  // Add a pairing
  val newPairing = "MARY" -> 678
  val newPhonebook = phoneBook + newPairing
  println(newPhonebook) // Map(Jim -> 555, Daniel -> 789, Mary -> 678)

  // Functionals on maps
  println(phoneBook.map(pair => pair._1.toLowerCase -> pair._2)) // prints Map(jim -> 555, daniel -> 789)

  // Filter keys
  // phoneBook.filterKeys(_.startsWith("J")) same as phoneBook.filterKeys(x => x.startsWith("J"))
  phoneBook.filterKeys(x => x.startsWith("J"))

  // filterKeys is deprecated use this one
  // Remember: x => x can be replaced with _. -> phoneBook.view.filterKeys(_.startsWith("J")).toMap)
  println(phoneBook.view.filterKeys(x => x.startsWith("J")).toMap) // prints Map(JIM -> 555)

  // Map values
  phoneBook.mapValues(number => number * 10)

  // mapValues is deprecated use this
  println(phoneBook.view.mapValues(number => number * 10).toMap) // prints Map(JIM -> 5550, DANIEL -> 7890)
  println(phoneBook.view.mapValues(number => "0245-" + number).toMap) // prints Map(JIM -> 0245-555, DANIEL -> 0245-789)

  // Conversion to other collections
  println(phoneBook.toList) // prints List((JIM,555), (DANIEL,789))
  println(List(("Daniel", 555)).toMap) // prints Map(Daniel -> 555)

  // prints HashMap(J -> List(James, Jim), A -> List(Angela), M -> List(Mary), B -> List(Bob), D -> List(Daniel))
  val names = List("Bob", "James", "Angela", "Mary", "Daniel", "Jim")
  println(names.groupBy(name => name.charAt(0)))

  // Exercises
  println("---- Exercises ----")
  val testPhoneBook = Map(("Jim", 555), "Daniel" -> 789, "JIM" -> 9000).withDefaultValue(-1)
  println(testPhoneBook) // prints Map(Jim -> 555, Daniel -> 789, JIM -> 9000)
  println(testPhoneBook.map(pair => pair._1.toLowerCase -> pair._2)) // prints Map(jim -> 9000, daniel -> 789)

  def add(network: Map[String, Set[String]], person: String): Map[String, Set[String]] = {
    network + (person -> Set())
  }

  def friend(network: Map[String, Set[String]], a: String, b: String): Map[String, Set[String]] = {
    val friendsA = network(a)
    val friendsB = network(b)
    network + (a -> (friendsA + b)) + (b -> (friendsB + a))
  }

  def unfriend(network: Map[String, Set[String]], a: String, b: String): Map[String, Set[String]] = {
    val friendsA = network(a)
    val friendsB = network(b)
    network + (a -> (friendsA - b)) + (b -> (friendsB - a))
  }

  def remove(network: Map[String, Set[String]], person: String): Map[String, Set[String]] = {
    def removeAux(friends: Set[String], networkAcc: Map[String, Set[String]]): Map[String, Set[String]] = {
      if (friends.isEmpty) networkAcc
      else removeAux(friends.tail, unfriend(networkAcc, person, friends.head))
    }
    val unfriended = removeAux(network(person), network)
    unfriended - person
  }

  def nFriends(network: Map[String, Set[String]], person: String): Int = {
    if (!network.contains(person)) 0
    else (network(person).size)
  }

  def mostFriends(network: Map[String, Set[String]]): String = {
    network.maxBy(pair => pair._2.size)._1
  }

  /*
    network.view.filter(pair => pair._2.isEmpty).size -> this one is also valid
    network.view.count(pair => pair._2.isEmpty) -> count is even better !
    network.view.count(_._2.isEmpty) -> same as network.view.count(pair => pair._2.isEmpty) !
  */
  def nPeopleWithNoFriends(network: Map[String, Set[String]]): Int = {
    network.view.filterKeys(k => network(k).isEmpty).size
  }

  def socialConnection(network: Map[String, Set[String]], a: String, b: String): Boolean = {
    @tailrec
    def bfs(target: String, consideredPeople: Set[String], discoveredPeople: Set[String]): Boolean = {
      if (discoveredPeople.isEmpty) false
      else {
        val person = discoveredPeople.head
        if (person == target) true
        else if (consideredPeople.contains(person)) bfs(target, consideredPeople, discoveredPeople.tail)
        else bfs(target, consideredPeople + person, discoveredPeople.tail ++ network(person))
      }
    }
    bfs(b, Set(), network(a) + a)
  }

  val empty: Map[String, Set[String]] = Map()
  val network = add(add(empty, "Bob"), "Gina")
  println(network) // prints Map(Bob -> Set(), Gina -> Set())
  println(friend(network, "Bob", "Gina")) // Make Bob and Gina friends ! -> Map(Bob -> Set(Gina), Gina -> Set(Bob))
  println(unfriend(friend(network, "Bob", "Gina"), "Bob", "Gina")) // Remove their friendship ! -> Map(Bob -> Set(), Gina -> Set())
  println(remove(friend(network, "Bob", "Gina"), "Bob")) // Remove Bob entirely -> Map(Gina -> Set())

  val people = add(add(add(empty, "Bob"), "Gina"), "Jim")
  val jimBob = friend(people, "Bob", "Jim")
  val peopleTest = friend(jimBob, "Bob", "Gina")
  println(peopleTest) // prints Map(Bob -> Set(Jim, Gina), Gina -> Set(Bob), Jim -> Set(Bob))
  println(nFriends(peopleTest, "Bob")) // prints 2
  println(mostFriends(peopleTest)) // prints Bob
  println(nPeopleWithNoFriends(peopleTest)) // prints 0
  println(socialConnection(peopleTest, "Gina", "Jim")) // prints true
  println(socialConnection(network, "Gina", "Bob")) // prints false

}