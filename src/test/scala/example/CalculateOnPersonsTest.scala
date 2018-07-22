package example

import org.scalatest._
import com.holdenkarau.spark.testing.SharedSparkContext
import org.apache.spark.rdd.RDD


class CalculateOnPersonsTest extends FunSuite with BeforeAndAfter with SharedSparkContext {
  val calculateOnPersons = new CalculateOnPersons(sc)
  var personsRDD :RDD[Person] = _

  before {
    personsRDD = sc.makeRDD(List(
      Person("Mike", 28),
      Person("Adam", 31),
      Person("John", 30))
    )
  }

  test("personsCount counts persons list") {
    assert(calculateOnPersons.personsCount(personsRDD) == 3)
  }

}
