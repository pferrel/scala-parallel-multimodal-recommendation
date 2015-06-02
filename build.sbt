name := "template-scala-parallel-recommendation"

organization := "io.prediction"

val mahoutVersion = "0.10.1"

libraryDependencies ++= Seq(
  "io.prediction"    %% "core" % pioVersion.value % "provided",
  "org.apache.spark" %% "spark-core" % "1.3.0" % "provided",
  "org.apache.spark" %% "spark-mllib" % "1.3.0" % "provided",
  "org.xerial.snappy" % "snappy-java" % "1.1.1.7",
  // Mahout's Spark code
  // used in 'pio build' with maven repos
  "org.apache.mahout" %% "mahout-math-scala" % mahoutVersion,
  "org.apache.mahout" %% "mahout-spark" % mahoutVersion
    exclude("org.apache.spark", "spark-core_2.10"),
  "org.apache.mahout"  % "mahout-math" % mahoutVersion,
  "org.apache.mahout"  % "mahout-hdfs" % mahoutVersion
    exclude("com.thoughtworks.xstream", "xstream")
    exclude("org.apache.hadoop", "hadoop-client"),
  "com.thoughtworks.xstream" % "xstream" % "1.4.4"
    exclude("xmlpull", "xmlpull"))
//
/* used while debugging
  "org.apache.mahout" % "mahout-math-scala_2.10" % mahoutVersion % "provided"
    from "file:///usr/local/mahout/math-scala/mahout-math-scala_2.10-0.10.1-SNAPSHOT.jar",
  "org.apache.mahout" % "mahout-spark_2.10" % mahoutVersion % "provided"
    from "file:///usr/local/mahout/spark/mahout-spark-scala_2.10-0.10.1-SNAPSHOT.jar",
  "org.apache.mahout" % "mahout-math" % mahoutVersion % "provided"
    from "file:///usr/local/mahout/math/mahout-math-0.10.1-SNAPSHOT.jar",
  "org.apache.mahout" % "mahout-hdfs" % mahoutVersion % "provided"
    from "file:///usr/local/mahout/hdfs/mahout-hdfs-0.10.1-SNAPSHOT.jar")
*/

  /* used in 'pio build' with local source
  "org.apache.mahout" % "mahout-math-scala_2.10" % mahoutVersion
    from "file:///usr/local/mahout/math-scala/mahout-math-scala_2.10-0.10.1-SNAPSHOT.jar",
  "org.apache.mahout" % "mahout-spark_2.10" % mahoutVersion
    from "file:///usr/local/mahout/spark/mahout-spark-scala_2.10-0.10.1-SNAPSHOT.jar",
  "org.apache.mahout" % "mahout-math" % mahoutVersion
    from "file:///usr/local/mahout/math/mahout-math-0.10.1-SNAPSHOT.jar",
  "org.apache.mahout" % "mahout-hdfs" % mahoutVersion
    from "file:///usr/local/mahout/hdfs/mahout-hdfs-0.10.1-SNAPSHOT.jar")
*/

//resolvers += "Apache staging" at " https://repository.apache.org/content/repositories/orgapachemahout-1009"

//resolvers += Resolver.mavenLocal

assemblyMergeStrategy in assembly := {
  case "plugin.properties" => MergeStrategy.discard
  case PathList(ps @ _*) if ps.last endsWith "package-info.class" =>
    MergeStrategy.first
  case x =>
    val oldStrategy = (assemblyMergeStrategy in assembly).value
    oldStrategy(x)
}
