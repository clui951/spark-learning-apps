scalaVersion := "2.11.12"

version := "0.0.1"

// https://mvnrepository.com/artifact/org.apache.spark/spark-core_2.11/2.3.1
libraryDependencies += "org.apache.spark" %% "spark-core" % "2.3.1"

// https://mvnrepository.com/artifact/com.holdenkarau/spark-testing-base_2.11/2.3.1_0.10.0
libraryDependencies += "com.holdenkarau" %% "spark-testing-base" % "2.3.1_0.10.0" % "test"

// https://oss.sonatype.org/content/groups/public/org/scalatest/scalatest_2.11/3.0.5/
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5" % "test"