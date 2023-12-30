package basics

object DefaultArgs extends App {

  //default paramater - Accumulator
  def trFact(n: Int, acc: Int = 1) : Int =
    if(n <= 1) acc
    else trFact(n - 1, acc * n)

  val fact10 = trFact(10) 
  println(fact10)

  /*default paramater should start from right
      1) either specify all args
      2) move default params to right side
      3) or while calling specify param name
   */
  def savePicture(format: String = "jpg", width: Int, height: Int): Unit = println("Saving picture")
  savePicture(height = 800, width = 900)
}
