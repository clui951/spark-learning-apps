# Spark Inverted Index

### Problem Statement
Given Shakespeare's works split amongst many files, create an inverted index of the words with a list of files in which it occurs and the number of times it occurs.

### Running ###
 ```
 $ sbt package
 $ spark-submit --master local[2] --class SparkInvertedIndex.Driver target/scala-2.11/spark-inverted-index_2.11-0.0.1.jar
 ```