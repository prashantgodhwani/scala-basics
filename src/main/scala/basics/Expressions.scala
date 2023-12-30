package basics

object Expressions extends App {

  val x: Int = 1 + 2
  println(x)

  //instructions/statements vs expressions
  val aCondition = true
  val aConditionValue = if(aCondition) 5 else 2
  println(aConditionValue)

  val aPartialCondition: Any = if(!aCondition) 5
  println(aPartialCondition) //prints Unit - ()

  //dont use while/for loops - only in Imperative code

  var i = 1
  val aWhile = while(i < 10){
    println(i)
    i += 1
  }

  println(aWhile) //side-effect - ()

  //Code Blocks
  //value of codeBlock is the value on the final expression - last line
  val aCodeBlock: Int = {
    val y = 2
    val z = y + 1

    if(z > 2) 3 else 4
  }

  print(aCodeBlock)
}
