package oops

object AnonymousClass extends App {
  abstract class Animal(name: String){
    def eat: Unit
    def greet: String = s"My name is $name"
  }

  //anonymous class - pass arguments irrespective if anonymous
  val funnyAnimal: Animal = new Animal("Jim") {
    override def eat: Unit = println("hahahaha")
  }

  //anonymous classes work for traits/abstract class/normal classes

  println(funnyAnimal.getClass)
  println(funnyAnimal.greet)
}
