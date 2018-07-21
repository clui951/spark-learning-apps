package example

import org.apache.spark.{SparkConf, SparkContext}

object SampleJob {
  def main(args: Array[String]) = {
    println("Hello, World!")

    val conf = new SparkConf()
      .setAppName("Spark-Sample-App")
      .setMaster("local[2]")

    val sc = new SparkContext(conf)

    val peopleRDD = sc.makeRDD(List(
      Person("Mike",28),
      Person("Adam", 31),
      Person("John", 30)
    ))

    val count = peopleRDD.count()
    println("There are " + count + " people.")

    val personsAbove30RDD = peopleRDD.filter(p => p.age > 30)
    println("RDD of personsAbove30: " + personsAbove30RDD.toString())
    println("Array of personsAbove30: " + personsAbove30RDD.collect().toString)

    val personsZipRDD = peopleRDD.zip(peopleRDD)
      .map(pair => pair._1.name + " " + pair._2.name)
    personsZipRDD.collect().foreach(p => println(p))

    val personsCartesianRDD = peopleRDD.cartesian(peopleRDD)
      .filter(pair => pair._1 != pair._2)
      .map(pair => pair._1.name + " " + pair._2.name)
    personsCartesianRDD.collect().foreach(p => println(p))

    sc.stop()
  }
}