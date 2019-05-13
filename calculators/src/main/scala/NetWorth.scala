object NetWorth extends App {
  def calculate(assets: Long, liabilities: Long): Long = assets - liabilities

  override def main(args: Array[String]): Unit = {
    val assets = args(0).toLong
    val liabilities = args(1).toLong
    println(s"Your net worth is ${calculate(assets, liabilities)}")
  }
}