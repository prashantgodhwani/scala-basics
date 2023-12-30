package functionalProgramming

import scala.util.Random

object Options extends App {
  val anOption: Option[Int] = Some(4)
  val noOption: Option[Int] = None

  println(anOption)

  //Work with unsafe APIs
  def unsafeMethod(): String = null
  //val result = Some(null) // wrong

  val result = Option(unsafeMethod())
  println(result)

  //chained method
  def backupMethod(): String = "Valid result"

  val chainedResult = Option(unsafeMethod()).orElse(Option(backupMethod()))
  println(chainedResult)

  //design unsafe APIs
  def betterUnsafeMethod(): Option[String] = None
  def betterBackupMethod(): Option[String] = Some("Valid result")
  val betterChainedResult = betterUnsafeMethod() orElse betterBackupMethod()
  println(betterChainedResult)

  //functions on Options
  println(anOption.isEmpty)
  println(anOption.get)

  //map, flatmap, filter
  println(anOption.map(_ * 2))
  println(anOption.filter(x => x > 10))
  println(anOption.flatMap(x => Option(x * 10)))

  //for comprehensions

  val config: Map[String, String] = Map("host" -> "176.42.361.25")

  class Connection{
    def connect = "Connected"
  }
  object Connection{
    val random = new Random(System.nanoTime())

    def apply(host: String, port: String): Option[Connection] =
      if(random.nextBoolean()) Some(new Connection)
      else None
  }

  def establishConnection() = {
    val host: Option[String] = Option(config("host"))
    val port: Option[String] = Option(config("port"))

    val connection = host.flatMap(host => port.flatMap(port => Connection(host, port)))
    val connectionStatus = connection.map(connection => connection.connect)
    println(connectionStatus)

  }
//  establishConnection()

  val connectionStatus = for{
    host <- config.get("host")
    port <- config.get("port")
    connection <- Connection(host, port)
  } yield connection.connect
  println(connectionStatus)
}
