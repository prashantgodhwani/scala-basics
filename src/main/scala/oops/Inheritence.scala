package oops

object Inheritence extends App {

  //single class Inheritence
  sealed class Animal{
    val creatureType = "Wild"
    def eat = println("nomnom")
  }

  class Cat extends Animal{
    def crunch = {
      eat
      println("Crunch Crunch")
    }
  }

  val cat: Cat = new Cat
  cat.crunch

  //constructors
  class Person(name:String, age: Int){
    def this(name: String) = this(name, 0)
  }

  class Adult(name: String, age: Int, idCard: String) extends Person(name, age)
  class AnotherAdult(name: String, age: Int, idCard: String) extends Person(name)

  //overidding
  class Dog(override val creatureType: String) extends Animal{
    override def eat: Unit = println("yum chum")
  }

  val dog: Animal = new Dog("Domestic")
  dog.eat

  /*
  prevent overrides
  1- use final on member
  2- use final on class
  3- use sealed, this allows override in same file, but not across multiple files
   */
}
