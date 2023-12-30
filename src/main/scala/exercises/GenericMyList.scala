package exercises

//trait MyPredicate[-T]{
//  def filter(el: T): Boolean
//}
//
//trait MyTransformer[-A, B]{
//  def apply(el: A) : B
//}

abstract class MyGenericList[+A] {
  def head: A
  def tail: MyGenericList[A]
  def isEmpty: Boolean
  def add[B >: A](el: B): MyGenericList[B]
  def printElements: String
  override def toString: String = "["+ printElements+"]"

  //higher order functions - receive/return functions
  def filter(predicate: A => Boolean): MyGenericList[A]
  def map[B](transformer: A => B): MyGenericList[B]
  def flatMap[B](transformer: A => MyGenericList[B]): MyGenericList[B]

  def ++[B >: A](list: MyGenericList[B]): MyGenericList[B]

  //hofs
  def foreach(f: A => Unit): Unit
  def sort(compare: (A, A) => Int): MyGenericList[A]
  def zipWith[B, C](list: MyGenericList[B], zip: (A, B) => C): MyGenericList[C]
  def fold[B](start: B)(operator: (B, A) => B): B
}

//Since List is Covariant and Nothing is proper substitute for any type.
//Hence, Empty is a substitute of MyList[Nothing]
case object EmptyGeneric extends MyGenericList[Nothing]{
  override def head: Nothing = throw new NoSuchElementException
  override def tail: MyGenericList[Nothing] = throw new NoSuchElementException
  override def isEmpty: Boolean = true
  override def add[B >: Nothing](el: B): MyGenericList[B] = new ConsGeneric(el, EmptyGeneric)
  override def printElements: String = ""

  override def map[B](transformer: Nothing => B): MyGenericList[B] = EmptyGeneric
  override def filter(predicate: Nothing => Boolean): MyGenericList[Nothing] = EmptyGeneric
  override def flatMap[B](transformer: Nothing => MyGenericList[B]): MyGenericList[B] = EmptyGeneric
  override def ++[B >: Nothing](list: MyGenericList[B]): MyGenericList[B] = list

  override def foreach(f: Nothing => Unit): Unit = ()
  override def sort(compare: (Nothing, Nothing) => Int): MyGenericList[Nothing] = EmptyGeneric
  override def zipWith[B, C](list: MyGenericList[B], zip: (Nothing, B) => C): MyGenericList[C] =
    if(!list.isEmpty) throw new RuntimeException("Cannot zip lists of different sizes")
    else EmptyGeneric
  override def fold[B](start: B)(operator: (B, Nothing) => B): B = start
}

case class ConsGeneric[+A](h: A, t: MyGenericList[A]) extends MyGenericList[A]{
  override def head: A = h
  override def tail: MyGenericList[A] = t
  override def isEmpty: Boolean = false
  override def add[B >: A](el: B): MyGenericList[B] = ConsGeneric(el, this)
  override def printElements: String =
    if(t.isEmpty) "" + h
    else s"${h} ${t.printElements}"

  /*
    [1,2,3].map(n * 2)
      = new Cons(2, [2,3].map(n * 2))
      = new Cons(2, new Cons(4, [3].map(n * 2)))
      = new Cons(2, new Cons(4, new Cons(6, Empty.map(n * 2))))
      = new Cons(2, new Cons(4, new Cons(6, Empty))))
 */
  override def map[B](transformer: A => B): MyGenericList[B] =
    ConsGeneric(transformer(h), t.map(transformer))

  /*
    [1,2,3].filter(n % 2 == 0) =
      [2,3].filter(n % 2 == 0) =
      = new Cons(2, [3].filter(n % 2 == 0))
      = new Cons(2, Empty.filter(n % 2 == 0))
      = new Cons(2, Empty)
 */
  override def filter(predicate: A => Boolean): MyGenericList[A] =
    if(predicate(h)) t.filter(predicate)
    else ConsGeneric(h, t.filter(predicate))

  /*
    [1,2].flatMap(n => [n, n+1])
    = [1,2] ++ [2].flatMap(n => [n, n+1])
    = [1,2] ++ [2,3] ++ Empty.flatMap(n => [n, n+1])
    = [1,2] ++ [2,3] ++ Empty
    = [1,2,2,3]
   */
  override def flatMap[B](transformer: A => MyGenericList[B]): MyGenericList[B] =
    transformer(h) ++ t.flatMap(transformer)

  /*
    [1,2] ++ [3,4,5]
    = new Cons(1, [2] ++ [3,4,5])
    = new Cons(1, new Cons(2, Empty ++ [3,4,5]))
    = new Cons(1, new Cons(2, new Cons(3, new Cons(4, new Cons(5)))))
  */
  override def ++[B >: A](list: MyGenericList[B]): MyGenericList[B] = new ConsGeneric[B](h, t ++ list)

  override def foreach(f: A => Unit): Unit = {
    f(h)
    t.foreach(f)
  }

  override def sort(compare: (A, A) => Int): MyGenericList[A] = {
    def insert(a: A, sortedTail: MyGenericList[A]): MyGenericList[A] = {
      if(sortedTail.isEmpty) ConsGeneric(a, EmptyGeneric)
      else if(compare(a, sortedTail.head) <= 0) ConsGeneric(a, sortedTail)
      else ConsGeneric(sortedTail.head, insert(a, sortedTail.tail))
    }

    val sortedTail = t.sort(compare)
    insert(h, sortedTail)
  }

  override def zipWith[B, C](list: MyGenericList[B], zip: (A, B) => C): MyGenericList[C] = {
    if(list.isEmpty) throw new RuntimeException("Cannot zip lists of different sizes")
    else new ConsGeneric[C](zip(head, list.head), tail.zipWith(list.tail, zip))
  }

  override def fold[B](start: B)(operator: (B, A) => B): B =
    tail.fold(operator(start, head))(operator)
}

object GenListTest extends App{
  val list = new ConsGeneric[Int](3, new ConsGeneric[Int](10, new ConsGeneric[Int](1, EmptyGeneric)))
  val anotherList = new ConsGeneric[Int](4, new ConsGeneric[Int](5, new ConsGeneric[Int](6, EmptyGeneric)))
  val listStr = new ConsGeneric[String]("Hello", new ConsGeneric[String]("Scala", new ConsGeneric[String]("!!", EmptyGeneric)))

  println(list.map(new Function1[Int, Double] {
    override def apply(el: Int): Double = el * 1.0
  }))

  println(list.map((el: Int) => el * 1.0))

  println(list.filter(_ % 2 != 1))

  println((list ++ anotherList).toString)

  println(list.flatMap(el => ConsGeneric(el, ConsGeneric(el + 1, EmptyGeneric))))

  println(list.copy() == list)

  list.foreach(println)

  list.sort((a, b) => b - a).foreach(println)

  println(anotherList.zipWith(listStr, _ + " " + _))

  println(list.fold(0)(_ + _))

  println {
    for {
      n <- list
      s <- listStr
    } yield n + "-" + s
  }

}