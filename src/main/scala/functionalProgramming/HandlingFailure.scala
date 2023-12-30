package functionalProgramming

import scala.util.{Failure, Random, Success, Try}

object HandlingFailure extends App {

  //create Success and Failure
  val aSuccess = Success(3)
  val aFailure = Failure(new RuntimeException("Super Failure"))
  println(aSuccess)
  println(aFailure)

  def unsafeMethod(): String = throw new RuntimeException("NO STRING FOR YOU")

  //Try objects
  val potentialFailure = Try(unsafeMethod())
  println(potentialFailure)

  //synctactic sugar
  val anotherPotentialFailure = Try{
    //code that might throw failure
  }

  //utils
  println(potentialFailure.isSuccess)
  println(potentialFailure.isFailure)

  //orElse
  def backupMethod(): String = "A temp result"
  val fallbackTry = Try(unsafeMethod()).orElse(Try(backupMethod()))
  println(fallbackTry)

  //if you design the API
  def betterUnsafeMethod(): Try[String] = Failure(new RuntimeException)
  def betterBackupMethod(): Try[String] = Success("Valid result")
  println(betterUnsafeMethod() orElse betterBackupMethod())

  //map, flatMap, filter
  println(aSuccess.map(_ * 2))
  println(aSuccess.flatMap(x => Success(x * 10)))
  println(aSuccess.filter(_ > 10))


  //exercises
  val host = "localhost"
  val port = "8080"
  def renderHTML(page: String) = println(page)

  class Connection{
    def get(url: String): String = {
      val random = new Random(System.nanoTime())
      if(random.nextBoolean()) "<html> </html>"
      else throw new RuntimeException("Conenction Interrupted")
    }

    def getSafe(url: String): Try[String] = Try(get(url))
  }

  object HttpService {
    val random = new Random(System.nanoTime())

    def getConnection(host: String, port: String): Connection = {
      if (random.nextBoolean()) new Connection
      else throw new RuntimeException("port unavailable")
    }

    def getSafeConnection(host: String, port: String): Try[Connection] = Try(getConnection(host, port))
  }

  val res = Try{
    val con = HttpService.getConnection(host, port)
    val page = con.get("Some URL")
    renderHTML(page)
  }
  println(res)

  val possibleConnection = HttpService.getSafeConnection(host, port)
  val possibleHTML = possibleConnection.flatMap(con => con.getSafe("/home"))
  possibleHTML.foreach(renderHTML)
}
