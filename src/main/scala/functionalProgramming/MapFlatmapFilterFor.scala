package functionalProgramming

object MapFlatmapFilterFor extends App {

  val list = List(1, 2, 3)
  println(list.head)
  println(list.tail)

  //map
  println(list.map(_ + 1))
  println(list.map(_ + "is a number"))

  //filter
  println(list.filter(_ % 2 == 0))

  //flatMap
  println(list.flatMap((x) => List(x, x + 1)))

  val numbers = List(1, 2, 3)
  val chars = List('a', 'b', 'c', 'd')

  println(chars.flatMap(ch => numbers.map(num => s"${ch}${num}")))

  val res = for{
    ch <- chars
    num <- numbers if num % 2 == 0
  } yield s"${ch}${num}"
  println(res)

  //side-effects
  for{
    n <- numbers
  } println(n)
}
