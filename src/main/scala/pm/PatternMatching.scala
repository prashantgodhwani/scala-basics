package pm

import scala.util.Random

object PatternMatching extends App {

  val random = new Random
  val x = random.nextInt(10)

  val desc = x match {
    case 1 => "One"
    case 2 => "Two"
    case _ => "We dont't care"
  }

  println(desc)

  //1. decompose values
  case class Person(name: String, age: Int)
  val bob = Person("Pra", 26)

  val greeting = bob match {
    case Person(n, a) if a >= 21 => s"Hi, my name is $n and I can drive"
    case Person(n, a) => s"Hi, my name is $n and I cant drive because I am $a years old"
    case _ => "bleh"
  }

  println(greeting)

  //PM on sealed hierachies
  //sealed classes help compiler gives you error for uncovered cases
  sealed class Animal
  case class Dog(breed: String) extends Animal
  case class Parrot(greet: String) extends Animal

  val animal: Animal = Dog("Terra Nova")
  animal match {
    case Dog(breed) => println(s"Woof woof, my breed is $breed")
  }

  //don't match everything - OVERKILL
  val isEven = x match {
    case n if n % 2 == 0 => true
    case _ => false
  }

  trait Expr
  case class Number(n: Int) extends Expr
  case class Sum(el: Expr, el2: Expr) extends Expr
  case class Prod(el: Expr, el2: Expr) extends Expr

  def toStringExpr(expr: Expr): String = {
    expr match {
      case Number(n) => ""+ n
      case Sum(a, b) => s"(${toStringExpr(a)} + ${toStringExpr(b)})"
      case Prod(a, b) => s"(${toStringExpr(a)} * ${toStringExpr(b)})"
    }
  }

  println(toStringExpr(Sum(Number(2), Prod(Number(5), Sum(Number(6), Number(7))))))
}
