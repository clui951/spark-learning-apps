package SparkInvertedIndex

import org.apache.spark.{SparkConf, SparkContext}

object Driver {
  def main(args: Array[String]) = {
    println("SparkInvertedIndex Driver")

    val conf = new SparkConf().setAppName("SparkInvertedIndex")
    val sc  = new SparkContext(conf)

    val stopwords = sc.textFile("dataset/stopwords.txt")
    val stopwordsBroadcast = sc.broadcast(stopwords)

  }
}
