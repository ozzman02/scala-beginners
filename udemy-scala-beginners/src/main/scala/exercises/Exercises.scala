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

        2. Add age to the Person class with default 0 value and add a unary + operator
          +mary => mary with the age incremented

        3. Add a "learns" method in the Person class => "Mary learns Scala"
           Add a "learnsScala" method, calls learns method with "Scala"
           Use it in postfix notation

        4. Overload the apply method
           mary.apply(2) => "Mary watched Inception 2 times"






  */

}
