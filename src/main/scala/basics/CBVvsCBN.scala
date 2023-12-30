package basics

object CBVvsCBN extends App {

  def calledByValue(x: Long) : Unit = {
    println("by value = " + x)
    println("by value = " + x)
  }

  def calledByName(x: => Long): Unit = {
    println("by name = " + x)
    println("by name = " + x)
  }

  //value evaluated and passed
  calledByValue(System.nanoTime())

  //expression passed, evaluated as many times as called
  calledByName(System.nanoTime())


  def infinite(): Int = 1 + infinite()
  def printFirst(x: Int, y: => Int) = println(x)

  printFirst(infinite(), 34) //crashes, evalauated by value so stack overflow
  printFirst(42, infinite()) // doesn't crash, because evaluated by name and isn't called

}
