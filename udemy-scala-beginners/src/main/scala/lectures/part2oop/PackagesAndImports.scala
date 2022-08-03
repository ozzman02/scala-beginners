package lectures.part2oop

import playground.{Cinderella, PrinceCharming}
import java.util.Date
import java.sql.{Date => SqlDate}

object PackagesAndImports extends App {

  // package members are accessible by their simple name
  val writer =  new Writer("Daniel", "RockTheJVM", 2018)

  // import package
  val princess = new Cinderella

  // fully qualified name
  val otherPrincess = new playground.Cinderella2

  // packages are in hierarchy
  // matching folder structure

  // package object
  sayHello
  println(SPEED_OF_LIGHT)

  // imports
  // import playground._
  val princeCharming = new PrinceCharming

  // alias
  val date = new Date
  val sqlDate = new SqlDate(2022, 8, 2)

  // default imports
  // java.lang - String, Object, Exception
  // scala - Int, Nothing, Function
  // scala.Predef - println, ???
}
