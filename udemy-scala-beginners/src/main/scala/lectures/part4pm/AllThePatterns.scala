package lectures.part4pm

import exercises.MyList
import exercises.Cons
import exercises.Empty

object AllThePatterns extends App {

  // 1. Constants
  val x: Any = "Scala"
  val constants = x match {
    case 1 => "a number"
    case "Scala" => "The Scala"
    case true => "The truth"
    case AllThePatterns => "A singleton object"
  }


  // 2. Match anything
  // 2.1 Wildcard
  val matchAnything = x match {
    case _ =>
  }

  // 2.2 Variable
  val matchVariable = x match {
    case something => s"I've found $something"
  }

  // 3. Tuples
  val aTuple = (1, 2)
  val matchAtuple = aTuple match {
    case (1, 1) =>
    case (something, 2) => s"I've found $something"
  }

  // PM can be nested !
  val nestedTuple = (1, (2, 3))
  val matchNestedTuple = nestedTuple match {
    case (_, (2, v)) =>
  }

  // 4. Case classes -> constructor pattern
  // PM can be nested with Case classes as well
  val aList: MyList[Int] = Cons(1, Cons(2, Empty))
  val matchAList = aList match {
    case Empty =>
    case Cons(head, tail) =>
    case Cons(head, Cons(subHead, subTail)) =>
  }

  // 5. List patterns
  val aStandardList = List(1, 2, 3, 43)
  val standardListMatching = aStandardList match {
    case List(1, _, _, _) => // extractor
    case List(1, _*) => // list of arbitrary length
    case 1 :: List(_) => // infix pattern
    case List(1, 2, 3) :+ 42 => // infix pattern
  }

  // 6. Type specifiers
  val unknown: Any = 2
  val unknownMatch = unknown match {
    case list: List[Int] => // explicit type specifier
    case _ =>
  }

  // 7. Name binding
  val nameBindingMatch = aList match {
    case nonEmptyList @ Cons(_, _) => // name binding => use the name later(here)
    case Cons(1, rest @ Cons(2, _)) => // name binding inside nested patterns
  }

  // 8. Multi Patterns
  val multipattern = aList match {
    case Empty | Cons(0, _) => // compound pattern (multi-pattern)
  }

  // 9. If guards
  val secondElementSpecial = aList match {
    case Cons(_, Cons(specialElement, _)) if specialElement % 2 == 0 =>
  }

  
}
