/**
  * This calculator computes the future value of your yearly regular deposits
  * at a given rate for given number of years
  * https://www.ajdesigner.com/phpinterest/interest_regular_deposits_p.php
  *
  * P = m (math.pow(1 + r / n, nt) -1)(n / r), where
  *
  */
object CompoundInterest extends App {
  /**
    *
    * @param m : the fixed amount you deposit
    * @param r : the annual rate of return
    * @param t : the number of years the amount is left to grow
    * @param n : the number of times amount is compounded per time period t, default is one per year
    * @return
    */
  def calculate(m: Double, r: Double, t: Int, n: Int = 1): Double = m * (math.pow(1 + r / n, n * t) - 1) * (n / r)

  override def main(args: Array[String]): Unit = {
    if (args.length < 3) {
      throw new IllegalArgumentException(s"CompoundInterest <principal> <rate> <years>")
    }

    val principalAmount = args(0).toDouble
    val rate = args(1).toDouble
    val numYears = args(2).toInt

    println(f"Your investment of $principalAmount%f will worth ${calculate(principalAmount, rate / 100, numYears)}%f in $numYears%d years at compounded rate of ${rate}%f percent.")
  }
}
