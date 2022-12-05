#!/bin/bash
# Clean old jar files
rm *.jar

# Compile
export HADOOP_LIPATH=/opt/cloudera/parcels/CDH-6.3.4-1.cdh6.3.4.p0.6626826/lib
javac -classpath $HADOOP_LIPATH/hadoop/*:$HADOOP_LIPATH/hadoop-mapreduce/*:$HADOOP_LIPATH/hadoop-hdfs/*:../lib/opencsv-5.7.1.jar   *.java
# javac -classpath `hadoop classpath`:../lib/opencsv-5.7.1.jar *.java

# Package
jar cf CountByHour.jar *.class

# Clean class files
rm *.class

# Run
hadoop jar CountByHour.jar CountByHour -libjars ../lib/opencsv-5.7.1.jar final/data/Clean_data.csv final/output/CountByHour
