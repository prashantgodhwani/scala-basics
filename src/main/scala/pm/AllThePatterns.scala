package pm

import exercises.{AllThePatterns, Any, App, Cons, Generics, MyList, constants, oops, x}

object AllThePatterns extends App {

  //1- constants
  val x: Any = "Scala"
  val constants = x match {
    case 1 => "one"
    case "Scala" => "Scalaaaa"
    case true => "Truth"
    case AllThePatterns => "Objectttt"
  }
  println(constants)

  //2- match anything
  //2.1 - wildcard
  val matchAnything = x match {
    case _ =>
  }

  //2.2 variable
  val matchAVar = x match {
    case something => s"Got $something"
  }

  val aTuple = (1, 2)
  val matchATuple = aTuple match {
    case (1, 1) =>
    case(_, 2) =>
  }

  val nestedTuple = (1, (2, 3))
  val nestedTupleMatch = nestedTuple match {
    case (_, (2, v)) =>
  }
  //PM can be nested

  //4 - case classes
//  val aList: MyList = Cons(1, Cons(2, Empty))
//  val matchList = aList match {
//    case Empty =>
//    case Cons(h, t) =>
//  }

  //5 - list patterns
  val aStandardList = List(1, 2, 3, 4)
  val standardListMatch = aStandardList match {
    case List(1, _, _, _) => //extractor
    case List(1, _*) => //list of arbitrary length
    case 1 :: List(_) => //infix pattern
    case List(1, 2, 3) :+ 4 => //infix pattern
  }

  //6 - type specifiers
  val unknown: Any = 2
  val unknownMatch = unknown match {
    case list: List[Int] => //explicit type specifier
    case _ =>
  }

  //7- name binding
  val nameBindingMatch = aStandardList match {
    case nonEmptyList @ Cons(_, _) => //name binding, use the name later(here)
    case Cons(1, rest @ Cons(2, _)) => // name binding nested patterns
  }

  //8- multi patterns
  val multipattern = aStandardList match {
    case Cons(2, Cons(3, _)) | Cons(0, _) => //compound pattern (multi pattern)
  }

  //9- if guards
  val secondElementSpecial = aStandardList match {
    case Cons(_, COns(specialEl, _)) if specialEl % 2 == 0 =>
  }

  val numbers = List(1, 2, 3)
  val numbersMatch = numbers match {
    case listOfStrings: List[String] => "a list of strings"
    case listOfString: List[Int] => "a list of ints"
  }
  println(numbersMatch)
  //JVM trick question - generics are erased at compile time



}
