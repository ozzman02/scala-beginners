package exercises

object Exercises {

  /*
    Expressions

      1. difference between "hello world" vs println("hello world?")

          Answer: "hello world" is a String and println("hello world?") is a Unit

      2. What is the value of:

          val someValue = {
            2 < 3
          }

          Answer: Outputs true

      3. What is the value of:

          val someValue = {
            2 < 3
          }

          Answer: Outputs true

      4. What is the value of:

          val someValue = {
            2 < 3
          }

          val someOtherValue = {
            if (someValue) 239 else 986
            42
          }

          Answer: 42
  */

  /*
    Functions

      1. Greeting function (name, age) => "Hi, my name is $name and I am $age years old."

          def aGreeting(name: String, age: Int): Unit = {
            println(s"Hi, my name is $name and I am $age years old.")
          }

          aGreeting("Oscar", 40)

      2. Factorial function 1 * 2 * 3 .. * n

          def aFactorial(number: Long):Long = {
            if (number <= 1) 1 else number * aFactorial(number - 1)
          }

          println(aFactorial(6))

      3. A Fibonacci function
          f(1) = 1
          f(2) = 1
          f(n) = f(n-1) + f(n-2)

          def anotherFibonacci(number: Int): Int = {
            if (number <= 2) 1 else anotherFibonacci(number - 1) + anotherFibonacci(number - 2)
          }

          println(anotherFibonacci(8));

      4. Test if a number is prime

          def isPrime(number: Int, i: Int): Boolean = {
            if (number <= 2) {
              if (number == 2) true else false
            } else if (number % i == 0) {
              false
            } else if (Math.sqrt(i) > number) {
              true
            } else {
              isPrime(number, i + 2)
            }
          }

          println(isPrime(29, 2))

  */

  /*
      Recursion

        1. Concatenate a string n times

          @tailrec
          def concatenateTailRec(aString: String, n: Int, accumulator: String): String = {
            if (n <= 0) accumulator
            else concatenateTailRec(aString, n - 1, aString + accumulator)
          }

        2. IsPrime function tail recursive

            def isPrime(n: Int): Boolean = {
              @tailrec
              def isPrimeTailRec(t: Int, isStillPrime: Boolean): Boolean = {
                if (!isStillPrime) false
                else if (t <= 1) true
                else isPrimeTailRec(t - 1, n % t != 0 && isStillPrime)
              }
              isPrimeTailRec(n / 2, true)
            }

        3. Fibonacci function, tail recursive

            def fibonacci(n: Int): Int = {
              @tailrec
              def fibonacciTailRec(i: Int, last: Int, nextToLast: Int): Int = {
                if (i >= n) last
                else fibonacciTailRec(i + 1, last + nextToLast, last)
              }
              if (n <= 2) 1
              else fibonacciTailRec(2, 1,1)
            }

  */

  /*
      OOP

        1. Implement a Novel and a Writer

          Writer: first name, surname, year
            - method fullName -> concatenation of first name and surname

           Novel: name, year of release, author (instance of writer)
            - method authorAge -> age of the author at the year of release
            - method isWrittenBy(Author)
            - method copy(new year of release) -> new instance of Novel
            
            Answer:
  
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

        2. Implement a Counter class
            - receives an Int
            - method current Count
            - method increment/decrement -> new Counter
            - overload increment/decrement to receive an int (increment or decrement by) -> new Counter

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

  */

  /*
      Method notations

        1. Overload the + operator
            mary + "the rockstar" => new person "Mary (the rockstar)"

              def +(nickName: String): Person = {
                new Person(s"$name ($nickName)", favoriteMovie)
              }

              println((mary + "The Rockstar")())
              println((mary + "The Rockstar").apply())

        2. Add age to the Person class with default 0 value and add a unary + operator
          +mary => mary with the age incremented

              def unary_+ : Person = new Person(name, favoriteMovie, age + 1)

              println((+mary).age)

        3. Add a "learns" method in the Person class => "Mary learns Scala"
           Add a "learnsScala" method, calls learns method with "Scala"
           Use it in postfix notation

              def learns(thing: String): String = {
                s"$name is learning $thing"
              }

              def learnsScala = this learns "Scala"

              println(mary learnsScala)

        4. Overload the apply method
           mary.apply(2) => "Mary watched Inception 2 times"

              def apply(n: Int): String = s"$name watched $favoriteMovie $n times"

              println(mary.apply(2))
  */

  /*
      Inheritance

        Create a MyList class

          - head = first element of the list
          - tail = remainder of the list
          - isEmpty = is the list empty?
          - add(int) = returns a new list with the element added
          - toString = a string representation of the list

            abstract class MyList {
              
              def head: Int
              
              def tail: MyList
              
              def isEmpty: Boolean
              
              def add(element: Int): MyList
              
              def printElements: String
              
              override def toString: String = "[" + printElements + "]"
  
            }

            object Empty extends MyList {

              override def head: Int = throw new NoSuchElementException()

              override def tail: MyList = throw new NoSuchElementException()

              override def isEmpty: Boolean = true

              override def add(element: Int): MyList = new Cons(element, Empty)

              override def printElements: String = ""

            }

            class Cons(h: Int, t: MyList) extends MyList {

              override def head: Int = h

              override def tail: MyList = t

              override def isEmpty: Boolean = false

              override def add(element: Int): MyList = new Cons(element, this)

              override def printElements: String = {
                if (t.isEmpty) "" + h
                else h + " " + t.printElements
              }

            }

            object LinkedListTest extends App {
              val list = new Cons(1, Empty)
              val anotherList = new Cons(1, new Cons(2, new Cons(3, Empty)))
              println(list.head)
              println(anotherList.tail.head)
              println(list.add(4).head)
              println(list.isEmpty)

              // Polymorphic call
              println(anotherList.toString)
            }

  */

  /*
      Generics

        1. Expand MyList to be generic

            abstract class MyGenericList[+A] {
              def head: A
              def tail: MyGenericList[A]
              def isEmpty: Boolean
              def add[B >: A](element: B): MyGenericList[B]
              def printElements: String
              override def toString: String = "[" + printElements + "]"
            }

          object OtherEmpty extends MyGenericList[Nothing] {
            override def head: Nothing = throw new NoSuchElementException()
            override def tail: MyGenericList[Nothing] = throw new NoSuchElementException()
            override def isEmpty: Boolean = true
            override def add[B >: Nothing](element: B): MyGenericList[B] = new OtherCons(element, OtherEmpty)
            override def printElements: String = ""
          }

          class OtherCons[+A](h: A, t: MyGenericList[A]) extends MyGenericList[A] {
            override def head: A = h
            override def tail: MyGenericList[A] = t
            override def isEmpty: Boolean = false
            override def add[B >: A](element: B): MyGenericList[B] = new OtherCons(element, this)
            override def printElements: String = {
              if (t.isEmpty) "" + h
              else s"$h ${t.printElements}"
            }
          }
  
    */

  /*
      Object Oriented

          1. Generic trait MyPredicate[-T] with a little method test(T) => boolean
          2. Generic trait MyTransformer[-A,B] with a method transform(A) => B
          3. MyList:
              -map(transformer) => MyList
              -filter(predicate) => MyList
              -flatMap(transformer from A to MyList[B]) => MyList[B]

              class EvenPredicate extends MyPredicate[Int]
              class StringToIntTransformer extends MyTransformer[String, Int]

              [1,2,3].map(n * 2) = [2,4,6]
              [1,2,3,4].filter(n % 2) = [2,4]
              [1,2,3].flatMap(n => [n, n + 1]) => [1,2,2,3,3,4]

              Answer:

                abstract class MyList[+A] {
                  def head: A
                  def tail: MyList[A]
                  def isEmpty: Boolean
                  def add[B >: A](element: B): MyList[B]
                  def printElements: String
                  def map[B](transformer: MyTransformer[A, B]): MyList[B]
                  def flatMap[B](transformer: MyTransformer[A, MyList[B]]): MyList[B]
                  def filter(predicate: MyPredicate[A]): MyList[A]
                  def ++[B >: A](list: MyList[B]): MyList[B]
                  override def toString: String = "[" + printElements + "]"
                }

                object Empty extends MyList[Nothing] {
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

                class Cons[+A](h: A, t: MyList[A]) extends MyList[A] {
                  override def head: A = h
                  override def tail: MyList[A] = t
                  override def isEmpty: Boolean = false
                  override def add[B >: A](element: B): MyList[B] = new Cons(element, this)
                  override def printElements: String = {
                    if (t.isEmpty) "" + h
                    else s"$h ${t.printElements}"
                  }
                  override def map[B](transformer: MyTransformer[A, B]): MyList[B] = {
                    new Cons(transformer.transform(h), t.map(transformer))
                  }
                  override def filter(predicate: MyPredicate[A]): MyList[A] = {
                    if (predicate.test(h)) {
                      new Cons(h, t.filter(predicate))
                    } else {
                      t.filter(predicate)
                    }
                  }
                  override def ++[B >: A](list: MyList[B]): MyList[B] = {
                    new Cons(h, t ++ list)
                  }
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

                  val listOfIntegers: MyList[Int] = new Cons(1, new Cons(2, new Cons(3, Empty)))
                  val listOfStrings: MyList[String] = new Cons("Hello", new Cons("Scala!!", Empty))
                  val anotherListOfIntegers: MyList[Int] = new Cons(4, new Cons(5, Empty))

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
                }

  */

  /*
      Object Oriented - Case Classes

            1. Expand MyList - use case classes and case objects

                Answer:

                  abstract class MyList[+A] {
                    def head: A
                    def tail: MyList[A]
                    def isEmpty: Boolean
                    def add[B >: A](element: B): MyList[B]
                    def printElements: String
                    def map[B](transformer: MyTransformer[A, B]): MyList[B]
                    def flatMap[B](transformer: MyTransformer[A, MyList[B]]): MyList[B]
                    def filter(predicate: MyPredicate[A]): MyList[A]
                    def ++[B >: A](list: MyList[B]): MyList[B]
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

                  class Cons[+A](h: A, t: MyList[A]) extends MyList[A] {
                    override def head: A = h
                    override def tail: MyList[A] = t
                    override def isEmpty: Boolean = false
                    override def add[B >: A](element: B): MyList[B] = new Cons(element, this)
                    override def printElements: String = {
                      if (t.isEmpty) "" + h
                      else s"$h ${t.printElements}"
                    }
                    override def map[B](transformer: MyTransformer[A, B]): MyList[B] = {
                      new Cons(transformer.transform(h), t.map(transformer))
                    }
                    override def filter(predicate: MyPredicate[A]): MyList[A] = {
                      if (predicate.test(h)) {
                        new Cons(h, t.filter(predicate))
                      } else {
                        t.filter(predicate)
                      }
                    }
                    override def ++[B >: A](list: MyList[B]): MyList[B] = {
                      new Cons(h, t ++ list)
                    }
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

                    val listOfIntegers: MyList[Int] = new Cons(1, new Cons(2, new Cons(3, Empty)))
                    val clonedListOfIntegers: MyList[Int] = new Cons(1, new Cons(2, new Cons(3, Empty)))
                    val listOfStrings: MyList[String] = new Cons("Hello", new Cons("Scala!!", Empty))
                    val anotherListOfIntegers: MyList[Int] = new Cons(4, new Cons(5, Empty))

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

  */

  /*
      Object Oriented - Exceptions

        1. Crash your program with an OutOfMemoryError

            val array = Array.ofDim[Int](Int.MaxValue)

        2. Crash with StackOverflowError

            def infinite: Int = 1 + infinite
            val noLimit = infinite

        3. PocketCalculator
            - add(x, y)
            - subtract(x, y)
            - multiply(x, y)
            - divide(x, y)

            Throw
              - OverflowException if add(x, y) exceeds INT.MAX_VALUE
              - UnderflowException if subtract(x, y) exceeds INT.MIN_VALUE
              - MathCalculationException for division by zero


              class OverflowException extends RuntimeException
              class UnderflowException extends RuntimeException
              class MathCalculationException extends RuntimeException("Division by 0")

              object PocketCalculator {

                def add(x: Int, y: Int): Int = {
                  val result = x + y
                  if (x > 0 && y > 0) throw new OverflowException
                  else if (x < 0 && y < 0 && result > 0) throw new UnderflowException
                  else result
                }

                def subtract(x: Int, y: Int): Int = {
                  val result = x - y
                  if (x > 0 && y < 0 && result < 0) throw new OverflowException
                  else if (x < 0 && y > 0 && result > 0) throw new UnderflowException
                  else result
                }

                def multiply(x: Int, y: Int): Int = {
                  val result = x * y
                  if (x > 0 && y > 0 && result < 0) throw new OverflowException
                  else if (x < 0 && y < 0 && result < 0) throw new OverflowException
                  else if (x > 0 && y < 0 && result > 0) throw new UnderflowException
                  else if (x < 0 && y > 0 && result > 0) throw new UnderflowException
                  else result
                }

                def divide(x: Int, y: Int): Int = {
                  if (y == 0) throw new MathCalculationException
                  else x / y
                }

              }

              //println(PocketCalculator.add(Int.MaxValue, 10))
              println(PocketCalculator.divide(2,0))



  */

  /*
      Functional programming

        1. Function which takes 2 strings and concatenates them

            val strJoiner = new ((String, String) => String) {
              override def apply(string1: String, string2: String): String = {
                string1.concat(string2)
              }
            }

          println(strJoiner("Hello ", "World"))

        2. Go to the list implementation and transfor MyPredicate and MyTransformer into FunctionTypes

            On class MyList[+A]

              // def map[B](transformer: Function1[A, B]): MyList[B]
              def map[B](transformer: (A) => B): MyList[B]

              // def flatMap[B](transformer: Function1[A, MyList[B]]): MyList[B]
              def flatMap[B](transformer: (A) => MyList[B]): MyList[B]

              // def filter(predicate: Function1[A, Boolean]): MyList[A]
              def filter(predicate: (A) => Boolean): MyList[A]

            On object Empty

              // override def map[B](transformer: MyTransformer[Nothing, B]): MyList[B] = Empty
              override def map[B](transformer: Nothing => B): MyList[B] = Empty

              // override def flatMap[B](transformer: MyTransformer[Nothing, MyList[B]]): MyList[B] = Empty
              override def flatMap[B](transformer: Nothing => MyList[B]): MyList[B] = Empty

              // override def filter(predicate: MyPredicate[Nothing]): MyList[Nothing] = Empty
              override def filter(predicate: Nothing => Boolean): MyList[Nothing] = Empty

            On class Cons

              /*
                override def map[B](transformer: MyTransformer[A, B]): MyList[B] = {
                  new Cons(transformer.transform(h), t.map(transformer))
                }
              */
              override def map[B](transformer: A => B): MyList[B] = {
                new Cons(transformer(h), t.map(transformer))
              }

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
                override def flatMap[B](transformer: MyTransformer[A, MyList[B]]): MyList[B] = {
                  transformer.transform(h) ++ t.flatMap(transformer)
                }
              */
              override def flatMap[B](transformer: A => MyList[B]): MyList[B] = {
                transformer(h) ++ t.flatMap(transformer)
              }

        3. Define a function which takes an int and returns another function which takes an int and returns an int
          - what's the type of the function
          - how to do it

            val superAdder: Function1[Int, Function1[Int, Int]] = new Function1[Int, Function1[Int, Int]] {
              override def apply(x: Int): Function1[Int,Int] = new Function1[Int, Int] {
                override def apply(y: Int): Int = x + y
              }
            }

            val superAdderCaller = superAdder(3)(4)
            println(superAdderCaller)
  */
  /*
      Anonymous Functions

        1. MyList: replace all functionX calls with lambdas

            println(listOfIntegers.map(element => element * 2).toString)
            println(listOfIntegers.map(_ * 2).toString)

            println(listOfIntegers.filter(element => element % 2 == 0).toString)
            println(listOfIntegers.filter(_ % 2 == 0).toString)

            Underscore notation can't be used here because we use the element two times in the implementation

              println(listOfIntegers.flatMap(element => new Cons(element, new Cons(element + 1, Empty))).toString)

        2. Rewrite the "special" adder as an anonymous function

            val superAdder: (Int) => (Int => Int) = new Function1[Int, Function1[Int, Int]] {
              override def apply(x: Int): (Int) => Int = new Function1[Int, Int] {
                override def apply(y: Int): Int = x + y
              }
            }
  
            val superAdd = (x: Int) => (y: Int) => x + y
            println(superAdd(3)(4))
  */
  /*
        HOFs & Curries

          1. Expand MyList

            - foreach method A => Unit

                [1,2,3].foreach(x => println(x))

                  abstract class MyList[+A] {
                    def foreach(f: A => Unit): Unit
                  }

                  case object Empty extends MyList[Nothing] {
                    override def foreach(f: Nothing => Unit): Unit = ()
                  }

                  case class Cons[+A](h: A, t: MyList[A]) extends MyList[A] {
                    override def foreach(f: A => Unit): Unit = {
                      f(h)
                      t.foreach(f)
                    }
                  }

                  object LinkedListTest extends App {
                    listOfIntegers.foreach(x => println(x))
                  }

            - sort function ((A,A) => Int) = MyList

                [1,2,3].sort((x, y) => y - x) => [3,2,1]

                    abstract class MyList[+A] {
                      def sort(compare: (A, A) => Int): MyList[A]
                    }

                    case object Empty extends MyList[Nothing] {
                      override def sort(compare: (Nothing, Nothing) => Int) = Empty
                    }

                    case class Cons[+A](h: A, t: MyList[A]) extends MyList[A] {
                      override def sort(compare: (A, A) => Int): MyList[A] = {
                        def insert(x: A, sortedList: MyList[A]): MyList[A] = {
                          if (sortedList.isEmpty) new Cons(x, Empty)
                          else if (compare(x, sortedList.head) <= 0) new Cons(x, sortedList)
                          else new Cons(sortedList.head, insert(x, sortedList.tail))
                        }
                        val sortedTail = t.sort(compare)
                        insert(h, sortedTail)
                      }
                    }

                    object LinkedListTest extends App {
                      println(listOfIntegers.sort((x, y) => y - x))
                    }

            - zipWith (list, (A, A) => B) => MyList[B]

                [1,2,3].zipWith([4,5,6], x * y) => [1 * 4, 2 * 5, 3 * 6] = [4, 10, 18]

                    abstract class MyList[+A] {
                      def zipWith[B, C](list: MyList[B], zip: (A, B) => C): MyList[C]
                    }

                    case object Empty extends MyList[Nothing] {
                      override def zipWith[B, C](list: MyList[B], zip: (Nothing, B) => C): MyList[C] = {
                        if (!list.isEmpty) throw new RuntimeException("Lists do not have the same length")
                        else Empty
                      }
                    }

                    case class Cons[+A](h: A, t: MyList[A]) extends MyList[A] {
                      override def zipWith[B, C](list: MyList[B], zip: (A, B) => C): MyList[C] = {
                        if (list.isEmpty) throw new RuntimeException("Lists do not have the same length")
                        else new Cons(zip(h, list.head), t.zipWith(list.tail, zip))
                      }
                    }

                    object LinkedListTest extends App {
                      println(anotherListOfIntegers.zipWith[String, String](listOfStrings, _ + "-" + _))
                    }

            - fold(start)(function) => a value (curried function)

                [1, 2, 3].fold(0)(x + y) = 6

                      abstract class MyList[+A] {
                        def fold[B](start: B)(operator: (B, A) => B): B
                      }

                      case object Empty extends MyList[Nothing] {
                        override def fold[B](start: B)(operator: (B, Nothing) => B): B = start
                      }

                      case class Cons[+A](h: A, t: MyList[A]) extends MyList[A] {
                        override def fold[B](start: B)(operator: (B, A) => B): B = {
                          t.fold(operator(start, h))(operator)
                        }
                      }

                      object LinkedListTest extends App {
                        println(listOfIntegers.fold(0)(_ + _))
                      }

          2. toCurry(f: (Int, Int) => Int) => (Int => Int => Int)
             fromCurry(f: (Int => Int => Int)) => (Int, Int) => Int

              def toCurry(f: (Int, Int) => Int): (Int => Int => Int) = {
                x => y => f(x, y)
              }

              def fromCurry(f: (Int => Int => Int)): (Int, Int) => Int = {
                (x, y) => f(x)(y)
              }

          3. compose(f, g) => x => f(g(x))
             andThen(f, g) => x => g(f(x))

              def compose[A,B,T](f: A => B, g: T => A): T => B = {
                x => f(g(x))
              }

              def andThen[A,B,C](f: A => B, g: B => C): A => C = {
                x => g(f(x))
              }

  */
  /*
      Map, FlatMap, Filter and for-comprehensions

        1. Does MyList support for-comprehensions?

            for-comprehensions are written by the compiler into chains of map, flatMaps and filters but for
            that to work we need a special signature for map, flatMap and filter

              map(f: A => B) => MyList[B]
              filter(p: A => Boolean) => MyList[A]
              flatMap(f: A => MyList[B]) => MyList[B]

        2. A small collection of at least one element - Maybe[+T]
          - map, flatMap, filter

            abstract class Maybe[+T] {
              def map[B](f: T => B): Maybe[B]
              def flatMap[B](f: T => Maybe[B]): Maybe[B]
              def filter(p: T => Boolean): Maybe[T]
            }

            case object MaybeNot extends Maybe[Nothing] {
              override def map[B](f: Nothing => B): Maybe[B] = MaybeNot
              override def flatMap[B](f: Nothing => Maybe[B]): Maybe[B] = MaybeNot
              override def filter(p: Nothing => Boolean): Maybe[Nothing] = MaybeNot
            }

            case class Just[+T](value: T) extends Maybe[T] {
              override def map[B](f: T => B): Maybe[B] = Just(f(value))
              override def flatMap[B](f: T => Maybe[B]): Maybe[B] = f(value)
              override def filter(p: T => Boolean): Maybe[T] = {
                if (p(value)) this
                else MaybeNot
              }
            }

            object MaybeTest extends App {
              val just3 = Just(3)
              println(just3)
              println(just3.map(_ * 2))                       // Just(6)
              println(just3.flatMap(x => Just(x % 2 == 0)))   // Just(false)
              println(just3.filter(_ % 2 == 0))               // MaybeNot
            }
  */
  /*
      Tuples and Maps

          1. What would happen if I had two original entries "Jim" -> 555 and "Jim" -> 900?

              Careful with mapping keys !!!

                val testPhoneBook = Map(("Jim", 555), "Daniel" -> 789, "JIM" -> 9000).withDefaultValue(-1)
                println(testPhoneBook) // prints Map(Jim -> 555, Daniel -> 789, JIM -> 9000)
                println(testPhoneBook.map(pair => pair._1.toLowerCase -> pair._2)) // prints Map(jim -> 9000, daniel -> 789)

          2. Overly simplified social network based on maps

            Person = String
              - add a person to the network
              - remove
              - friend (mutual)
              - unfriend

              - number of friends of the person
              - person with most friends
              - how many people have no friends
              - if there is a social connection between two people (direct or not)

            Answer

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
              println(friend(network, "Bob", "Gina"))
              println(unfriend(friend(network, "Bob", "Gina"), "Bob", "Gina"))
              println(remove(friend(network, "Bob", "Gina"), "Bob"))

              val people = add(add(add(empty, "Bob"), "Gina"), "Jim")
              val jimBob = friend(people, "Bob", "Jim")
              val peopleTest = friend(jimBob, "Bob", "Gina")
              println(peopleTest)
              println(nFriends(peopleTest, "Bob"))
              println(mostFriends(peopleTest))
              println(nPeopleWithNoFriends(peopleTest))
              println(socialConnection(peopleTest, "Gina", "Jim"))
              println(socialConnection(network, "Gina", "Bob"))
  */
  /*
       Options

        val config: Map[String, String] = Map("host" -> "176.45.36.1", "port" -> "90")

        class Connection {
          def connect = "Connected" // connect to some server
        }

        object Connection {
          val random = new Random(System.nanoTime())
          def apply(host: String, port: String): Option[Connection] = {
          if (random.nextBoolean()) Some(new Connection)
            else None
          }
        }

        val host = config.get("host")
        val port = config.get("port")

        val connection: Option[Connection] = host.flatMap(h => port.flatMap(p => Connection.apply(h, p)))
        val connectionStatus: Option[String] = connection.map(c => c.connect)

        println(connectionStatus)
        connectionStatus.foreach(println)

        /* Chained method solution */
        config.get("host")
          .flatMap(host => config.get("port")
          .flatMap(port => Connection(host, port))
          .map(connection => connection.connect))
          .foreach(println)
  
        /* For-Comprehensions */
        val forConnectionStatus = for {
          host <- config.get("host")
          port <- config.get("port")
          connection <- Connection(host, port)
        } yield connection.connect

        forConnectionStatus.foreach(println)
  */
  /*
      Patter Matching

        1. Simple function uses PM takes an Expr => human readable form

          Sum(Number(2), Number(3)) => 2 + 3
          Sum(Sum(Number(2), Number(3)), Number(4)) => 2 + 3 + 4
          Prod(Sum(Number(2), Number(1)), Number(3)) => (2+1) * 3
          Sum(Prod(Number(2), Number(1)), Number(3)) => 2 * 1 + 3

            trait Expr
            case class Number(n: Int) extends Expr
            case class Sum(e1: Expr, e2: Expr) extends Expr
            case class Prod(e1: Expr, e2: Expr) extends Expr

            def show(e: Expr): String = e match {
              case Number(n) => s"$n"
              case Sum(e1, e2) => show(e1) + " + " + show(e2)
              case Prod(e1, e2) =>
                def maybeShowParentheses(expr: Expr) = expr match {
                  case Prod(_, _) => show(expr)
                  case Number(_) => show(expr)
                  case _ => "(" + show(expr) + ")"
                }
                maybeShowParentheses(e1) + " * " + maybeShowParentheses(e2)
            }

            println(show(Sum(Number(2), Number(3))))
            println(show(Sum(Sum(Number(2), Number(3)), Number(4))))
            println(show(Prod(Sum(Number(2), Number(1)), Number(3))))
            println(show(Sum(Prod(Number(2), Number(1)), Number(3))))

            println(show(Prod(Sum(Number(2), Number(1)), Sum(Number(3), Number(4)))))
  */

}
