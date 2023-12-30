package basics

import scala.annotation.tailrec

object Functions extends App {

    //its fine to omit return types for normal functions
    def sayHello(name: String, age: Int) =
      println(s"Hello, my name is ${name} and age is ${age}")

    val functionReturn = sayHello("Prashant", 26)
    println(functionReturn)

    def parameterLessFunction() = 3
    println(parameterLessFunction())

    //recursive functions should have specified return types
    def printName(str: String, times: Int): Unit = {
      if(times == 0) return;
      println(str)
      printName(str, times - 1)
    }

    printName("Prashant", 5)

    //functions can come inside functions - unlike JAVA
    def aBigFucntion(n: Int): Int = {
      def aSmallerFunction(a: Int, b: Int): Int = a + b

      aSmallerFunction(n, n - 1)
    }

    println(aBigFucntion(5))

    def factorial(n: Int): Int = {
        @tailrec
        def aggFactorial(n: Int, agg: Int): Int = {
          if (n == 1) return agg * n
          aggFactorial(n - 1, agg * n)
        }
        aggFactorial(n, 1)
    }

    println(factorial(5))

    def fibonacci(n: Int): Int = {
      @tailrec
      def aggFonacci(i: Int, aggN: Int, aggN2: Int): Int = {
        if (i >= n) return aggN
        aggFonacci(i + 1, aggN + aggN2, aggN)
      }

      if(n <= 2) return 1
      aggFonacci(2, 1, 1)
    }

    println(fibonacci(8))

  def isPrime(n: Int): Boolean = {
    @tailrec
    def isPrimeAgg(n: Int, cur: Int, agg: Boolean = true): Boolean = {
      if (cur <= 1) agg
      else isPrimeAgg(n, cur - 1, agg && (n % cur == 0))
    }

    isPrimeAgg(n - 1, Math.sqrt(n).toInt)
  }

  println(isPrime(6))
}
