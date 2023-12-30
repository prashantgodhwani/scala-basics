package functionalProgramming

object WhatsAFunction extends App {
  //function types
  val stringToIntConverter = new Function1[String, Int] {
    override def apply(str: String): Int = str.toInt
  }

  println(stringToIntConverter("123"))

  //(Int, Int) => Int is synctatic sugar for Function2[Int, Int, Int]
  val adder: (Int, Int) => Int = new Function2[Int, Int, Int] {
    override def apply(a: Int, b: Int): Int = a + b
  }
  println(adder(2, 3))

  //ALL SCALA FUNCTIONS ARE OBJECTS
  def ++ : (String, String) => String = new Function2[String, String, String] {
    override def apply(v1: String, v2: String): String = v1 + v2
  }
  println("abs" ++ "concat")

  def add3: (Int => (Int => Int)) = new Function[Int, (Int => Int)] {
    override def apply(v1: Int): Int => Int = (a => v1 + a + 3)
  }
  println(add3(2)(3))

  def shorterAdd = (x: Int) => (y: Int) => x + y + 3
  println(shorterAdd(2)(3))

  //no params
  val doSomething: () => Int = () => 3
  //careful
  println(doSomething)
  println(doSomething())

  //curly braces with Lambdas
  val stringToInt = { (str: String) =>
    str.toInt
  }

  //more Synctatic Sugar
  val niceIncrementer: Int => Int = _ + 1 //equivalent to x => x + 1
  val niceAdder: (Int, Int) => Int = _ + _ //equivalent to (x, b) => x + b

}
