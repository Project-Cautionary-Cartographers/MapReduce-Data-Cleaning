#!/bin/bash
# Clean old jar files
rm *.jar

# Compile
export HADOOP_LIPATH=/opt/cloudera/parcels/CDH-6.3.4-1.cdh6.3.4.p0.6626826/lib
javac -classpath $HADOOP_LIPATH/hadoop/*:$HADOOP_LIPATH/hadoop-mapreduce/*:$HADOOP_LIPATH/hadoop-hdfs/*   *.java
# hadoop com.sun.tools.javac.Main *.java

# Package
jar cf Clean.jar *.class

# Run
hadoop jar Clean.jar Clean final/data/NYPD_Complaint_Data_Current__Year_To_Date_.csv final/Clean_old

# Clean class files
rm *.class
