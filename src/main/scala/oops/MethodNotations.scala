package oops

import scala.language.postfixOps

object MethodNotations extends App{

  class Person(val name: String, favoriteMove: String, val age: Int = 0){
    def likes(movie: String): Boolean = movie == favoriteMove
    def +(person: Person): String = s"${this.name} is hanging out with ${person.name}"
    def +(str: String): Person = new Person(s"${this.name} ($str)", this.favoriteMove, this.age)
    def unary_+(): Person = new Person(this.name, this.favoriteMove, this.age + 1)
    def unary_! : String = s"${name}!!!!"
    def isAlive: Boolean = true
    def apply(): String = s"Hi, my name is $name and I like $favoriteMove"

    def learns(sub: String): String = s"${this.name} loves ${sub}"
    def learnsScala: String = learns("Scala")
    def apply(times: Int) = s"${this.name} watched ${favoriteMove} ${times} times"
  }

  val mary = new Person("Mary", "Inception", 5)
  println(mary.likes("Inception"))

  //infix-operator notation
  //only works with methods that have single arguments
  println(mary likes "Inception")

  //operators in Scala
  val tom = new Person("Tom", "Fight Club", 10)
  println(mary + tom)

  //All operators are method
  println(1.+(2))

  //prefix notation
  val x = -1
  val y = 1.unary_-

  //unary_ prefix only works with - + ~ !
  println(!mary)
  println(mary.unary_!)

  //postfix notation
  println(mary.isAlive)
  println(mary isAlive)

  //apply
  println(mary.apply())
  println(mary())

  println((mary + "the Rockstar")())

//  println((+mary).age)
  println(mary learnsScala)
  println(mary(2))
}
