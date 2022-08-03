package exercises

abstract class MyList[+A] {

  def head: A

  def tail: MyList[A]

  def isEmpty: Boolean

  def add[B >: A](element: B): MyList[B]

  def printElements: String

  def map[B](transformer: MyTransformer[A, B]): MyList[B]

  def flatMap[B](transformer: MyTransformer[A, MyList[B]]): MyList[B]

  def filter(predicate: MyPredicate[A]): MyList[A]

  // Concatenation method
  def ++[B >: A](list: MyList[B]): MyList[B]

  // Polymorphic call
  override def toString: String = "[" + printElements + "]"

}

case object Empty extends MyList[Nothing] {

  override def head: Nothing = throw new NoSuchElementException()

  override def tail: MyList[Nothing] = throw new NoSuchElementException()

  override def isEmpty: Boolean = true

  override def add[B >: Nothing](element: B): MyList[B] = new Cons(element, Empty)

  override def printElements: String = ""

  override def map[B](transformer: MyTransformer[Nothing, B]): MyList[B] = Empty

  override def flatMap[B](transformer: MyTransformer[Nothing, MyList[B]]): MyList[B] = Empty

  override def filter(predicate: MyPredicate[Nothing]): MyList[Nothing] = Empty

  override def ++[B >: Nothing](list: MyList[B]): MyList[B] = list

}

case class Cons[+A](h: A, t: MyList[A]) extends MyList[A] {

  override def head: A = h

  override def tail: MyList[A] = t

  override def isEmpty: Boolean = false

  override def add[B >: A](element: B): MyList[B] = new Cons(element, this)

  override def printElements: String = {
    if (t.isEmpty) "" + h
    else s"$h ${t.printElements}"
  }

  /*
    [1,2,3].map(n * 2)
      = new Cons(2, [2,3].map(n * 2))
      = new Cons(2, new Cons(4, [3].map(n * 2)))
      = new Cons(2, new Cons(4, new Cons(6, Empty.map(n * 2))))
      = new Cons(2, new Cons(4, new Cons(6, Empty)))
  */
  override def map[B](transformer: MyTransformer[A, B]): MyList[B] = {
    new Cons(transformer.transform(h), t.map(transformer))
  }

  /*
    [1, 2, 3].filter(n % 2 == 0) =
      [2, 3].filter(n % 2 == 0) =
        = new Cons(2, [3].filter(n % 2 == 0))
        = new Cons(2, Empty.filter(n % 2 == 0))
        = new Cons(2, Empty)
  */
  override def filter(predicate: MyPredicate[A]): MyList[A] = {
    if (predicate.test(h)) {
      new Cons(h, t.filter(predicate))
    } else {
      t.filter(predicate)
    }
  }

  /*
    [1,2] ++ [3,4,5]
      = new Cons(1, [2] ++ [3,4,5])
      = new Cons(1, new Cons(2, Empty ++ [3,4,5]))
      = new Cons(1, new Cons(2, new Cons(3, new Cons(4, new Cons(5)))))
  */
  override def ++[B >: A](list: MyList[B]): MyList[B] = {
    new Cons(h, t ++ list)
  }

  /*
    [1,2].flatMap(n => [n, n + 1])
      = [1,2] ++ [2].flatMap(n => [n, n + 1])
      = [1,2] ++ [2,3] ++ Empty.flatMap(n => [n, n + 1])
      = [1,2] ++ [2,3] ++ Empty
      = [1,2,2,3]
  */
  override def flatMap[B](transformer: MyTransformer[A, MyList[B]]): MyList[B] = {
    transformer.transform(h) ++ t.flatMap(transformer)
  }

}

trait MyPredicate[-T] {
  def test(element: T): Boolean
}

trait MyTransformer[-A, B] {
  def transform(element: A): B
}

object LinkedListTest extends App {

  /*
    Test code with the original implementation
  */
  val list = new Cons(1, Empty)
  val anotherList = new Cons(1, new Cons(2, new Cons(3, Empty)))
  println(list.head)
  println(anotherList.tail.head)
  println(list.add(4).head)
  println(list.isEmpty)

  // Polymorphic call
  println(anotherList.toString)

  /*
    Test code with the modified implementation (generics, map, filter, flatMap, case classes)
  */
  val listOfIntegers: MyList[Int] = new Cons(1, new Cons(2, new Cons(3, Empty)))
  val listOfStrings: MyList[String] = new Cons("Hello", new Cons("Scala!!", Empty))
  val anotherListOfIntegers: MyList[Int] = new Cons(4, new Cons(5, Empty))

  val clonedListOfIntegers: MyList[Int] = new Cons(1, new Cons(2, new Cons(3, Empty)))

  println(listOfIntegers.toString)
  println(listOfStrings.toString)

  println(listOfIntegers.map(new MyTransformer[Int, Int] {
    override def transform(element: Int): Int = element * 2
  }).toString)

  println(listOfIntegers.filter(new MyPredicate[Int] {
    override def test(element: Int): Boolean = {
      element % 2 == 0
    }
  }).toString)

  println((listOfIntegers ++ anotherListOfIntegers).toString)

  println(listOfIntegers.flatMap(new MyTransformer[Int, MyList[Int]] {
    override def transform(element: Int): MyList[Int] = new Cons(element, new Cons(element + 1, Empty))
  }).toString)

  // This will print true
  println(clonedListOfIntegers == listOfIntegers)

}