package example

import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD

class CalculateOnPersons(sc: SparkContext) {
  val personsRDD = sc.makeRDD(List(
    Person("Mike",28),
    Person("Adam", 31),
    Person("John", 30))
  )

  def personsCount() :Long = {
    personsRDD.count()
  }

  def personsAbove30() :RDD[Person] = {
    personsRDD.filter(person => person.age > 30)
  }

  def personsZippedWithSelf() :RDD[(Person, Person)] = {
    personsRDD.zip(personsRDD)
  }

  def personsUniquePairings() :RDD[(Person, Person)] = {
    personsRDD.cartesian(personsRDD).filter(pair => pair._1 != pair._2)
  }

}
