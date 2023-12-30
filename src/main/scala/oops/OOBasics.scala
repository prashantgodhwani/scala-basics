package oops

import java.io.Writer

object OOBasics extends App {
  val person = new Person("Prashant", 26)
  println(person.age)
  person.greet("John")
  person.greet()

  val auth1: Author = new Author("Charles", "Dickens", 1812)
  val auth2: Author = new Author("Fake Charles", "Dickens", 1812)
  val novel1: Novel = new Novel("Greatest Expectations", 1861, auth1)

  println(novel1.authorAge())
  println(novel1.isWrittenBy(auth2))
  println(novel1.copy(2025).authorAge())

  val c = new Counter(10)
  c.incrementByValue(8).print

  c.increment.increment.increment.print
}

//constructor - Person(name: String, age: Int)
//class Person(name: String, age: Int)

//class parameters are not class Members
class Person(name: String, val age: Int){
  //body - is evaluated
  //vals, defs, constructor, expressions
  val x = 2        //class members

  println("Class body execution")

  //method overloading
  def greet(name: String): Unit = println(s"${this.name} says: Hi, $name")
  def greet(): Unit = println(s"Hi, I am ${this.name}")

  //overloading constructors - oveloaded constructors can only call base contructor and nothing else
  def this(name: String) = {
    this(name, 0);
  }
}

class Author(val fname: String, val lName: String, val year: Int){
  def fullName() = s"$fname $lName"

  override def equals(obj: Any): Boolean = {
    if(!obj.isInstanceOf[Author]) return false;
    val objAuth: Author = obj.asInstanceOf[Author]
    objAuth.fname.equals(fname) && objAuth.lName.equals(this.lName) && objAuth.year.equals(this.year)
  }
}

class Novel(name: String, yor: Int, author: Author){
  def authorAge() = yor - author.year
  def isWrittenBy(author: Author) = this.author.equals(author)
  def copy(newYear: Int) = new Novel(this.name, newYear, this.author)
}

class Counter(val count: Int){
  def currentCount: Int = count
  def increment: Counter = new Counter(count + 1)
  def decrement: Counter = new Counter(count - 1)
  def incrementByValue(x: Int): Counter =
    if(x <= 0) this
    else increment.incrementByValue(x - 1)
  def decrementByValue(x: Int): Counter =
    if (x <= 0) this
    else decrement.decrementByValue(x - 1)
  def print = println(this.currentCount)
}