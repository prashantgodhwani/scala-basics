package oops

object AbstractDataTypes extends App {

  //abstract types
  abstract class Animal {
    val creatureType: String = "Wild"
    def eat: Unit
  }

  class Dog extends Animal{
    override val creatureType: String = "Canine"
    override def eat: Unit = println("Crunch Crunch")
  }

  //traits
  trait Carnivore{
    def eat(animal: Animal): Unit
    val preferedMeal: String = "fresh meat"
  }

  trait ColdBlooded

  class Crocodil extends Animal with Carnivore with ColdBlooded {
    override val creatureType: String = "Croc"
    override def eat: Unit = println("Non Nom")
    override def eat(animal: Animal): Unit = println(s"Croc eating a ${animal.creatureType}")
  }

  val dog = new Dog
  val crocodil = new Crocodil
  crocodil.eat(dog)

  //traits vs abstract classes
  //1- traits do not have constructor parameters
  //2- multiple traits may be inherited by the same class
  //3- traits = behavior, abstract class = "thing"
}
