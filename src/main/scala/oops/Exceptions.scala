package oops

object Exceptions extends App {
  val x: String = null
   //println(x.length)
  // this ^^ will crash with NPE

  //aWeirdValue can be anything, because Nothing - NullPointerException is a subtype of everything
//  val aWeirdValue: String = throw new NullPointerException()
//  println(aWeirdValue)

  //catch Exceptions
  def getInt(shouldThrow: Boolean): Int = {
    if(shouldThrow) throw new RuntimeException("No Integer for you")
    else 42
  }

  val potentialFail: Int = try{
    getInt(false)
  }catch {
    case e : RuntimeException => 42
  }finally {
    //code executed no matter what
    //optional
    //does not influence the return type
    //use finally for side effects
    println("Finally")
  }
}
