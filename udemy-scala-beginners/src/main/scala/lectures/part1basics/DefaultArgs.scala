package lectures.part1basics

import scala.annotation.tailrec

object DefaultArgs extends App {

  @tailrec
  def trFactorial(n: Int, acc: Int = 1): Int = {
    if (n <= 1) acc
    else trFactorial(n - 1, n * acc)
  }
  val fact10 = trFactorial(10)

  /*
    1. Pass in every leading argument
    2. Name the arguments
  */
  def savePicture(format: String = "jpg", width: Int = 1920, height: Int = 1080): Unit = println("saving picture")
  savePicture("bmp")
  savePicture(width = 800)
  savePicture(height = 600, width = 800, format = "jpg")


}
