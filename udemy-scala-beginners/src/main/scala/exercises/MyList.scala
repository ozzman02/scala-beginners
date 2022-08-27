package exercises

abstract class MyList[+A] {

  def head: A

  def tail: MyList[A]

  def isEmpty: Boolean

  def add[B >: A](element: B): MyList[B]

  def printElements: String

  // def map[B](transformer: Function1[A, B]): MyList[B]
  def map[B](transformer: (A) => B): MyList[B]

  // def flatMap[B](transformer: Function1[A, MyList[B]]): MyList[B]
  def flatMap[B](transformer: (A) => MyList[B]): MyList[B]

  // def filter(predicate: Function1[A, Boolean]): MyList[A]
  def filter(predicate: (A) => Boolean): MyList[A]

  // Concatenation method
  def ++[B >: A](list: MyList[B]): MyList[B]

  // Polymorphic call
  override def toString: String = "[" + printElements + "]"

  // HOFs
  def foreach(f: A => Unit): Unit

  def sort(compare: (A, A) => Int): MyList[A]

  def zipWith[B, C](list: MyList[B], zip: (A, B) => C): MyList[C]

  def fold[B](start: B)(operator: (B, A) => B): B

}

case object Empty extends MyList[Nothing] {

  override def head: Nothing = throw new NoSuchElementException()

  override def tail: MyList[Nothing] = throw new NoSuchElementException()

  override def isEmpty: Boolean = true

  override def add[B >: Nothing](element: B): MyList[B] = new Cons(element, Empty)

  override def printElements: String = ""

  /* map, flatMap and filter are Higher-Order functions
      Higher-Order functions either receive functions as parameters or return functions as result
  */

  // override def map[B](transformer: MyTransformer[Nothing, B]): MyList[B] = Empty
  override def map[B](transformer: Nothing => B): MyList[B] = Empty

  // override def flatMap[B](transformer: MyTransformer[Nothing, MyList[B]]): MyList[B] = Empty
  override def flatMap[B](transformer: Nothing => MyList[B]): MyList[B] = Empty

  // override def filter(predicate: MyPredicate[Nothing]): MyList[Nothing] = Empty
  override def filter(predicate: Nothing => Boolean): MyList[Nothing] = Empty

  override def ++[B >: Nothing](list: MyList[B]): MyList[B] = list

  // HOFs
  override def foreach(f: Nothing => Unit): Unit = ()

  override def sort(compare: (Nothing, Nothing) => Int) = Empty

  override def zipWith[B, C](list: MyList[B], zip: (Nothing, B) => C): MyList[C] = {
    if (!list.isEmpty) throw new RuntimeException("Lists do not have the same length")
    else Empty
  }

  override def fold[B](start: B)(operator: (B, Nothing) => B): B = start

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
  /*
    override def map[B](transformer: MyTransformer[A, B]): MyList[B] = {
      new Cons(transformer.transform(h), t.map(transformer))
    }
  */
  override def map[B](transformer: A => B): MyList[B] = {
    new Cons(transformer(h), t.map(transformer))
  }

  /*
    [1, 2, 3].filter(n % 2 == 0) =
      [2, 3].filter(n % 2 == 0) =
        = new Cons(2, [3].filter(n % 2 == 0))
        = new Cons(2, Empty.filter(n % 2 == 0))
        = new Cons(2, Empty)
  */
  /*
    override def filter(predicate: MyPredicate[A]): MyList[A] = {
      if (predicate.test(h)) {
        new Cons(h, t.filter(predicate))
      } else {
        t.filter(predicate)
      }
    }
  */
  override def filter(predicate: A => Boolean): MyList[A] = {
    if (predicate(h)) {
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
  /*
    override def flatMap[B](transformer: MyTransformer[A, MyList[B]]): MyList[B] = {
      transformer.transform(h) ++ t.flatMap(transformer)
    }
  */
  override def flatMap[B](transformer: A => MyList[B]): MyList[B] = {
    transformer(h) ++ t.flatMap(transformer)
  }

  // HOFs

  override def foreach(f: A => Unit): Unit = {
    f(h)
    t.foreach(f)
  }

  override def sort(compare: (A, A) => Int): MyList[A] = {

    def insert(x: A, sortedList: MyList[A]): MyList[A] = {
      if (sortedList.isEmpty) new Cons(x, Empty)
      else if (compare(x, sortedList.head) <= 0) new Cons(x, sortedList)
      else new Cons(sortedList.head, insert(x, sortedList.tail))
    }

    val sortedTail = t.sort(compare)

    insert(h, sortedTail)
  }

  override def zipWith[B, C](list: MyList[B], zip: (A, B) => C): MyList[C] = {
    if (list.isEmpty) throw new RuntimeException("Lists do not have the same length")
    else new Cons(zip(h, list.head), t.zipWith(list.tail, zip))
  }

  /*
      [1, 2, 3].fold(0)(+) =
      = [2, 3].fold(1)(+)
      = [3].fold(3)(+)
      = [].fold(6)(+)
      = 6
  */
  override def fold[B](start: B)(operator: (B, A) => B): B = {
    t.fold(operator(start, h))(operator)
  }
}

/*trait MyPredicate[-T] {
  def test(element: T): Boolean
}

trait MyTransformer[-A, B] {
  def transform(element: A): B
}*/

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

  /*
    println(listOfIntegers.map(new (Int => Int) {
      override def apply(element: Int): Int = element * 2
    }).toString)
  */
  println(listOfIntegers.map(element => element * 2).toString)

  /*
    println(listOfIntegers.filter(new (Int => Boolean) {
      override def apply(element: Int): Boolean = {
        element % 2 == 0
      }
    }).toString)
  */
  println(listOfIntegers.filter(element => element % 2 == 0).toString)

  println((listOfIntegers ++ anotherListOfIntegers).toString)

  /*
    println(listOfIntegers.flatMap(new (Int => MyList[Int]) {
      override def apply(element: Int): MyList[Int] = new Cons(element, new Cons(element + 1, Empty))
    }).toString)
  */
  println(listOfIntegers.flatMap(element => new Cons(element, new Cons(element + 1, Empty))).toString)

  // This will print true
  println(clonedListOfIntegers == listOfIntegers)

  // HOFs
  listOfIntegers.foreach(x => println(x))
  println(listOfIntegers.sort((x, y) => y - x))
  println(anotherListOfIntegers.zipWith[String, String](listOfStrings, _ + "-" + _))
  println(listOfIntegers.fold(0)(_ + _))

  // for-comprehensions
  val combinations = for {
    n <- listOfIntegers
    string <- listOfStrings
  } yield n + "-" + string

  println(combinations)

  // a for-comprehension is also an expression
  println(
    for {
      n <- listOfIntegers
      string <- listOfStrings
    } yield n + "-" + string
  )

}