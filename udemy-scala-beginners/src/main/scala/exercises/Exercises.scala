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
}
