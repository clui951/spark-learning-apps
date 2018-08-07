package SparkInvertedIndex

import org.apache.spark.{SparkConf, SparkContext}

object Driver {
  def main(args: Array[String]) = {

    val conf = new SparkConf().setAppName("SparkInvertedIndex")
    val sc  = new SparkContext(conf)

    // Read stopwords into a broadcast variable
    val stopwords = sc.textFile("dataset/stopwords.txt")
    val stopwordsBroadcast = sc.broadcast(stopwords.collect().toSet)

    val shakespeareText = sc.wholeTextFiles("dataset/shakespeare/")
    println("There are " + shakespeareText.count().toString() + " partitions.")

    val rawRes = shakespeareText.flatMap{
      case (path, text) =>
        text.replaceAll("[^\\w\\s]|('s|ly|ed|ing|ness) ", " ")
          .split("""\W+""")
          .filter(!stopwordsBroadcast.value.contains(_))
          .map(word => (word, path))
    }.map{
      // (w,p) to ((w,p),1); trim path to only file name
      case (w, p) => ((w, p.split("/").last), 1)
    }.reduceByKey{
      // add (w,p) counts together
      case (c1, c2) => c1 + c2
    }.map{
      // change tuple format
      case ((w, p), c) => (w, (p, c))
    }.groupBy{
      // group by word to (w, CompactBuffer[(w,(p,c))])
      case (w, (p, c)) => w
    } // sort by size of CompactBuffer
      .sortBy(_._2.size, false)

    val res = rawRes.map{
      case (w, seq) =>
        val seqString = seq.map{
          t => t._2
        }.mkString(", ")
        (w, seqString)
    }

    res.take(15).foreach(println(_))

    println("DONE!")
  }
}
