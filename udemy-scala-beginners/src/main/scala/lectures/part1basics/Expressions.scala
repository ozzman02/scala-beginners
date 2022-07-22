package lectures.part1basics

object Expressions extends App {

  val x = 1 + 2 // Expression
  println(x)
  println(2 + 3 * 4)
  // + - * / & | ^ << >> >>> (right shift with zero extension)

  // Basic expression examples
  val number = 3 + 5
  println(number)
  val numberIsEven = number % 2 == 0
  val numberIsOdd = !numberIsEven
  println(numberIsEven)
  println(numberIsOdd)



  println(1 == x)
  // == != > >= < <=

  println(!(1 == x))
  // ! && ||

  var aVariable = 2
  aVariable += 3 // also works with -= *= /= .... side effects

  println(aVariable)

  // Instructions (Do) vs Expressions (Value or Type)

  // IF Expression
  val aCondition = true
  val aConditionValue = if (aCondition) 5 else 3
  println(aConditionValue)
  // Printing expression not an instruction. The expression returns a value
  println(if (aCondition) 5 else 3)

  // NEVER WRITES THIS AGAIN
  var i = 0
  while (i < 10) {
    println(i)
    i += 1
  }

  // While produces a side effect which returns Unit
  val aWhile = while (i < 10) {
    println(i)
    i += 1
  }


  // Everything in Scala is an expression. Side effects are expressions that returns Unit values
  // Side Effects: println(), whiles, reassigning

  val aWeirdValue = (aVariable = 3) // Unit type === void
  println(aWeirdValue) // Returns ()

  // Code block
  val aCodeBlock = {
    val y = 2
    val z = y + 1
    if (z > 2) "hello" else "goodbye"
  }
  
}
