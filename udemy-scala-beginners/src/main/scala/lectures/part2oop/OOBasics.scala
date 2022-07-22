package lectures.part2oop

object OOBasics extends App {

  val person = new Person("John", 26)
  println(person.age)
  println(person.x)
  person.greet("Daniel")
  person.greet()


  val author = new Writer("Charles", "Dickens", 1812)
  val anotherAuthor = new Writer("Xiang", "Xing", 1815)
  val thirdAuthor = new Writer("Charles", "Dickens", 1812)
  val novel = new Novel("Great Expectations", 1861, author)
  println(author.fullName())
  println(novel.authorAge())
  println(novel.isWrittenBy(author))
  println(novel.isWrittenBy(anotherAuthor))
  println(novel.isWrittenBy(thirdAuthor))
  println(novel.copy(1862))

  val counter = new Counter(10)
  counter.print()
  counter.inc.print()
  counter.dec.print()
  //counter.inc(3).print()
  //counter.dec(3).print()

}

// Constructor
// Class parameters are not fields
// In order to convert a class parameter to a field is by adding the keyword val or var
class Person(name: String, val age: Int = 0) {
  // body
  val x = 2 // field

  println(1 + 3) // expression

  def greet(name: String): Unit = println(s"${this.name} says: Hi, $name") // method

  // overloading
  def greet(): Unit = println(s"Hi, I am $name")

  // multiple constructors
  def this(name: String) = this(name, 0)

  def this() = this("Oscar")
}

class Writer(val firstName: String, val surname: String, val year: Int) {
  def fullName(): String = {
    this.firstName.concat(" ").concat(this.surname)
  }
  override def toString = s"Writer($firstName, $surname, $year)"
}

class Novel(val name: String, val yearOfRelease: Int, val author: Writer) {

  def authorAge(): Int = {
    this.yearOfRelease - this.author.year
  }

  def isWrittenBy(author: Writer): Boolean = {
    this.author == author
  }

  def copy(newYearOfRelease: Int): Novel = {
    new Novel(this.name, newYearOfRelease, this.author)
  }

  override def toString = s"Novel($name, $yearOfRelease, $author)"

}

class Counter(val count: Int = 0) {

  def inc: Counter = {
    println("Incrementing")
    new Counter(this.count + 1)
  }

  def dec: Counter = {
    println("Decrementing")
    new Counter(this.count - 1)
  }

  def inc(n: Int): Counter = {
    if (n <= 0) this
    else inc.inc(n-1)
  }

  def dec(n: Int): Counter = {
    if (n <= 0) this
    else dec.dec(n-1)
  }

  def print(): Unit = println(count)

}