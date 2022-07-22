package lectures.part1basics

// Call By Name vs Call By Value
object CBNvsCBV extends App {

  // Here same value of x is used inside the method
  // Value is computed before call
  // Same value is used everywhere
  def callByValue(x: Long): Unit = {
    println("by value: " + x) // 36144663562531
    println("by value: " + x) // 36144663562531
  }

  // The arrow indicates that the parameter is going to be evaluated everytime producing different results
  // By name parameter delays the evaluation until it is used.
  // Expression is passed literally
  // Expression is evaluated at every use within
  def callByName(x: => Long): Unit = {
    println("by name: " + x) // 36144733570394
    println("by name: " + x) // 36144734343811
  }

  callByValue(System.nanoTime())
  callByName(System.nanoTime())

  def infinite(): Int = 1 + infinite()
  def printFirst(x: Int, y: => Int) = println(x)

  //printFirst(infinite(), 34) // StackOverflowError

  // Here the function infinite() is not used therefore, it is not evaluated
  printFirst(34, infinite())


}
