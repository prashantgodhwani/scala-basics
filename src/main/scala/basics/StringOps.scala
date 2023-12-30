package basics

object StringOps extends App {

  val str: String = "hello I am learning Scala"

  println(str.charAt(2))
  println(str.substring(7, 11))


  val aNumberString = "45"
  val aNumber = aNumberString.toInt

  //scala
  println('a' +: aNumberString :+ 'b')
  println(str.reverse)
  println(str.take(2))

  //Scala specific: String interpolators
  //S-Interpolaters
  val name: String = "Prashant"
  val age = 12
  val greeting = s"Hi my name is $name and age is $age"
  val anotherGreeting = s"Hi my name is $name and will be turning is ${age + 1}"

  //F-Interpolators
  val speed = 1.2f
  val myth = f"$name can eat $speed%2.3f burgers per minute"
  println(myth)

  //raw-Interpolator
  println(raw"This is \n a new line")
}
