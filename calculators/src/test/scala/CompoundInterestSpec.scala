import org.scalatest.{FlatSpec, Matchers}

class CompoundInterestSpec extends FlatSpec with Matchers {
  "A Compound Interest Calculator" should "return more than 62000 in return" in {
    CompoundInterest.calculate(5000, 5, 10) should be(6.0466175E10)
  }
}
