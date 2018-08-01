package example

import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD

class CalculateOnPersons(sc: SparkContext) {

  def personsCount(personsRDD :RDD[Person]) :Long = {
    personsRDD.count()
  }

  def personsAbove30(personsRDD :RDD[Person]) :RDD[Person] = {
    personsRDD.filter(person => person.age > 30)
  }

  def personsZippedWithSelf(personsRDD :RDD[Person]) :RDD[(Person, Person)] = {
    personsRDD.zip(personsRDD)
  }

  def personsUniquePairings(personsRDD :RDD[Person]) :RDD[(Person, Person)] = {
    personsRDD.cartesian(personsRDD).filter(pair => pair._1 != pair._2)
  }

}
