package basics

object ValuesVariablesTypes extends App{

  val x: Integer = 42
  println(x)

  //val's cannot be re-assigned. They are immutable
  //x = 2

  //compiler can infer types
  //no need to add types
  val y = 42
  println(y)


  val aString = "Hello"
  val aBoolean = true
  val aCharacter: Char = 'a'
  val aShort: Short = 232
  val aLong: Long = 2342342343342434234L
  val aFloat: Float = 2.0f
  val aDouble: Double = 2.03

  //variables
  //immutable, can be re-assigned
  var aVariable: Int = 10
  aVariable = 2   //side effects

  
}
