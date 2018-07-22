package example

import org.apache.spark.{SparkConf, SparkContext}

object SampleJob {
  def main(args: Array[String]) = {
    println("Hello, World!")

    val conf = new SparkConf()
      .setAppName("Spark-Sample-App")
      .setMaster("local[2]")

    val sc = new SparkContext(conf)

    val personsRDD = sc.makeRDD(List(
        Person("Mike",28),
        Person("Adam", 31),
        Person("John", 30))
    )

    val personsCalculations = new CalculateOnPersons(sc)

    val count = personsCalculations.personsCount(personsRDD)
    println(count)

    sc.stop()
  }
}