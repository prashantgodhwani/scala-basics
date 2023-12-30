package functionalProgramming

object HOFsCurries extends App {

  //higher order functions
  //val superFunction: (Int, (String, (Int => Boolean)) => Int) => (Int => Int) = ???

  //function that applies a function n times over a value x
  def applyN(f: Int => Int, x: Int, n: Int): Int = {
    if(n == 1) f(x)
    else applyN(f, f(x), n - 1)
  }

  val res = applyN((_ * 2), 2, 5)
  println(res)

  def nTimesBetter(f: Int => Int, n: Int): (Int => Int) = {
    if(n == 1) (x: Int) => f(x)
    else (x: Int) => nTimesBetter(f, n - 1)(f(x))
  }

  val plusOne = (x: Int) => x + 1
  val plus10 = nTimesBetter(plusOne, 10)
  println(plus10(1))

  //curried function
  //=> is right associative
  //Int => Int => Int   =  Int => (Int => Int)
  val superAdder: Int => Int => Int = (x: Int) => (y: Int) => x + y

  def curriedFormatter(c: String)(x: Double): String = c.format(x)
  val standardFormatter: Double => String = curriedFormatter("%4.2f")
  val preciseFormatter: Double => String = curriedFormatter("%4.10f")

  println(standardFormatter(Math.PI))
  println(preciseFormatter(Math.PI))

  def compose(f: Int => Int, g: Int => Int): (Int => Int) = (x) => f(g(x))
  def andThen(f: Int => Int, g: Int => Int): (Int => Int) = (x) => g(f(x))

  println(compose(x => x * 2, y => y + 3)(5))
  println(andThen(x => x * 2, y => y + 3)(5))

  def fromCurry(f: (Int, Int) => Int): (Int => Int => Int) = x => y => f(x, y)
  def toCurry(f: Int => Int => Int): ((Int, Int) => Int) = (x, y) => f(x)(y)
}
