package basics

import scala.annotation.tailrec

object Recursion extends App {

  def factorial(n: Int): BigInt = {
    @tailrec
    def aggFactorial(n: Int, agg: BigInt): BigInt = {
      if (n <= 1) agg
      else aggFactorial(n - 1, agg * n)
    }

    aggFactorial(n, 1)
  }
  println(factorial(50))


  def isPrime(n: Int): Boolean = {
    @tailrec
    def isPrimeAgg(n: Int, cur: Int, agg: Boolean = true): Boolean = {
      if(!agg) agg
      else if (cur <= 1) agg
      else isPrimeAgg(n, cur - 1, agg && (n % cur == 0))
    }

    isPrimeAgg(n - 1, Math.sqrt(n).toInt)
  }
  println(isPrime(6))
}
