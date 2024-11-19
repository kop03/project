package org.ZCY.pack

import org.apache.spark.SparkConf
import java.util.Properties
import org.apache.spark.sql.SparkSession

object connect {
  def SparkHive(): SparkSession = {
    val conf = new SparkConf().setAppName("task").setMaster("local")
    val spark = SparkSession.builder().config(conf).enableHiveSupport().getOrCreate()
    spark.conf.set("hive.exec.dynamic.partition.mode", "nonstrict")
    spark.conf.set("spark.sql.storeAssignmentPolicy","LEGACY")

    spark.sparkContext.setLogLevel("OFF")
    spark
  }

  def MySQLCon(): Properties = {
    val properties = new Properties()
    properties.put("user", "root")
    properties.put("password", "1234")
    properties.put("driver", "com.mysql.jdbc.Driver")
    properties
  }

  def geturl(): String = {
    val url = "jdbc:mysql://192.168.88.10:3306/comm"
    url
  }
}
