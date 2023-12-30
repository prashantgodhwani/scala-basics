package oops

object Objects extends App {
  //Scala does not have Class-Level Functionality ("static")

  //class level functionality
  object Person{
    val N_EYES = 2
    def canFly: Boolean = false

    //factory-method
    def apply(mother: Person, father: Person) = new Person("Bobbie")
  }
  //instance level functionality
  class Person(val name: String){

  }
  //companions

  println(Person.N_EYES)
  println(Person.canFly)

  //Scala object is a Singleton Instance
  val mary = Person
  val john = Person
  println(mary == john)

  val maryy = new Person("Mary")
  val johnn = new Person("John")
  println(maryy == johnn)

  val bobbie = Person(maryy, johnn)
  println(bobbie)
}
