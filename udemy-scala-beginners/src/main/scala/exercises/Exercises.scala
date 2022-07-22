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

}
