package functionalProgramming

import scala.util.Random

object Sequences extends App {
  val aSequence = Seq(1, 2, 3, 4)
  println(aSequence)
  println(aSequence.reverse)
  println(aSequence(2))
  println(aSequence ++ Seq(5, 6, 7))
  println(aSequence.sorted)

  //Ranges
  val aRange: Seq[Int] = 1 to 10
  aRange.foreach(println)

  val anUntilRange: Seq[Int] = 1 until 10
  anUntilRange.foreach(println)

  (1 to 10).foreach(x => println("Hello: " + x))

  //Lists
  val aList = List(1, 2, 3)
  val prepend = 42 +: aList :+ 10
  println(prepend)

  val apples5 = List.fill(5)("apple")
  println(apples5)

  println(aList.mkString("-|-"))


  //Arrays
  val numbers = Array(1, 2, 3, 4)
  val treeElements = Array.ofDim[Int](3)
  println(numbers)
  println(treeElements)

  //mutation
  numbers(2) = 0
  println(numbers.mkString(" "))

  //arrays and sequences
  val numbersSeq: Seq[Int] = numbers
  println(numbersSeq)

  //vectors - implemented as trie
  val vector: Vector[Int] = Vector(1, 2, 3)
  println(vector)

  //vectors v/s lists
  val maxRuns = 1000
  val maxCapacity = 1000000
  def getWriteTime(collection: Seq[Int]): Double = {
    val r = new Random
    val times = for{
      itr <- 1 to maxRuns
    } yield {
      val currentTime = System.nanoTime()
      //operation
      collection.updated(r.nextInt(maxCapacity), 0)
      System.nanoTime() - currentTime
    }
    times.sum * 1.0 / maxRuns
  }

  val numbersList = (1 to maxCapacity).toList
  val numbersVector = (1 to maxCapacity).toVector

  //keep reference to tail
  //updating element in middle takes time
  println(getWriteTime(numbersList))
  //depth of tree is small
  //needs to replace an entire 32-element chunk
  println(getWriteTime(numbersVector))

}
