package oops

object Generics extends App {

  //covariant
  class MyList[+A]{
    //use thr type A
    def add[B >: A](element: B): MyList[B] = ???
    /*
      A = Cat
      B = Animal
     */
  }
  val listOfIntegers = new MyList[Int]
  val listOfStrings = new MyList[String]

  object MyList{
    //functions with generics
    def empty[A]: MyList[A] = ???
  }
  val emptyListOfIntegers = MyList.empty[Int]

  class MyMap[Key, Value]

  //variance
  class Animal
  class Cat extends Animal
  class Dog extends Animal

  //1. yes, List[Cat] extends List[Animal] = COVARIANCE
  class CovariantList[+A]
  val animal: Animal = new Cat
  val animalList: CovariantList[Animal] = new CovariantList[Dog]
  //animalList.add(new Dog)??? HARD QUESTION
  //answer is that if we add a Dog to list[Cat] then it is a list[Animal]
  //this is what will happen

  //2. no = INVARIANCE
  class InVariantList[A]
//  val inVariantAnimalList: InVariantList[Animal] = new InVariantList[Dog]

  //3. Hell, no = CONTRAVARIANCE
  class ContravariantList[-A]
  val contravariantList: ContravariantList[Dog] = new ContravariantList[Animal]

  //CONTRAVARIANT, but this time, it makes sense because an Animal Trainer can train a Dog
  class Trainer[-A]
  val trainers: Trainer[Dog] = new Trainer[Animal]

  //bounded types
  //can only hold subtypes of Animal - UPPER BOUNDED
  class Cage[A <: Animal](animal: A)
  val cage = new Cage[Dog](new Dog)
  val catCage = new Cage[Cat](new Cat)

  //LOWER BOUNDED
}
