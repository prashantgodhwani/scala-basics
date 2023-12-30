package functionalProgramming

object TuplesAndMaps extends App {

  val aTuple = (2, "Hello, Scala")
  println(aTuple._1)
  println(aTuple.copy(_2 = "TADA Java"))
  println(aTuple.swap)

  //maps
  val aMap: Map[String, Int] = Map()

  val phoneBook = Map(("Jim", 555), ("Daniel" -> 789), ("JIM", 900)).withDefaultValue(-1)
  //a -> b == (a, b)

  println(phoneBook)
  println(phoneBook.contains("Jim"))
  println(phoneBook("Jim"))
  //throws nosuch exception, if defaultValue not specified
  println(phoneBook("efef"))

  val newPairing = "Mary" -> 678
  val newPhonebook = phoneBook + newPairing
  println(newPhonebook)

  //map, flatmap, filter
  println(newPhonebook.map(pair => pair._1.toLowerCase -> pair._2))
  println(newPhonebook.view.filterKeys(x => x.startsWith("J")).foreach(println))

  println(newPhonebook.view.mapValues(number => "0245-" + number).foreach(print))

  //to other collections
  println(newPhonebook.toList)
  println(List(("Dan", 555)).toMap)

  //Group By - very IMPPPPPP
  val names = List("Bob", "James", "Angela", "Mary", "Daniel", "Jim")
  println(names.groupBy(name => name.charAt(0)))


  val network : Map[String, Set[String]] = Map()

  def add(person: String, network: Map[String, Set[String]]) = network + (person -> Set())
  def remove(person: String, network: Map[String, Set[String]]) = {
    def removeAux(friends: Set[String], networkAcc: Map[String, Set[String]]): Map[String, Set[String]] =
      if(friends.isEmpty) networkAcc
      else removeAux(friends.tail, unfriend(person, friends.head, networkAcc))

    val unfriended = removeAux(network(person), network)
    unfriended - person
  }
  def friend(p1: String, p2: String, network: Map[String, Set[String]]) = {
    network + (p1 -> (network(p1) + p2)) + (p2 -> (network(p2) + p1))
  }

  def unfriend(p1: String, p2: String, network: Map[String, Set[String]]) = {
    network + (p1 -> (network(p1) - p2)) + (p2 -> (network(p2) - p1))
  }

  def nFriends(person: String, network: Map[String, Set[String]]): Int =
    if(!network.contains(person)) 0
    else network(person).size

  def mostFriends(network: Map[String, Set[String]]): String =
    network.maxBy(pair => pair._2.size)._1

  def noFriends(network: Map[String, Set[String]]): Int =
    network.count(pair => pair._2.isEmpty)

  def socialConnection(network: Map[String, Set[String]], a: String, b: String): Boolean = {
    def bfs(target: String, consideredPeople: Set[String], discoveredPeople: Set[String]): Boolean = {
      if(discoveredPeople.isEmpty) false
      else {
        val person = discoveredPeople.head
        if(person == target) true
        else if(consideredPeople.contains(person)) bfs(target, consideredPeople, discoveredPeople.tail)
        else bfs(target, consideredPeople + person, discoveredPeople.tail ++ network(person))
      }
    }
    bfs(b, Set(), network(a) + a)
  }

  val op1 = add("Prashant", network)
  println(op1)
  val op2 = add("Sam", op1)
  println(op2)
  val op3 = friend("Prashant", "Sam", op2)
  println(op3)
  val op4 = unfriend("Prashant", "Sam", op3)
  println(op4)
  val op5 = add("Prateek", op3)
  println(op5)
  val op6 = friend("Prashant", "Prateek", op5)
  println(op6)
  val op7 = remove("Prashant", op6)
  println(op7)
  println(mostFriends(op6))
}
