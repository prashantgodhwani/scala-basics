package oops

object CaseClasses extends App {
  case class Person(name: String, age: Int)

  //1. class parameters are fields
  val jim = new Person("Prashant", 26)
  println(jim.name)

  //2. to String
  println(jim.toString)

  //3. equals and hashCode implemented = OOTB
  val jim2 = new Person("Prashant", 26)
  println(jim == jim2)

  //4. CCs have handy copy methods
  val jim3 = jim.copy(age = 35)
  println(jim3)

  //5. CCs have companion objects
  val thePerson = Person
  val mary = Person("Mary", 22)  //don't use constructors, use this to create new objects
  println(mary)

  //6. CCs are serializable
  //Akka

  //7. CCs have extractor patterns = CCs can be used in PATTERN MATCHING
}
