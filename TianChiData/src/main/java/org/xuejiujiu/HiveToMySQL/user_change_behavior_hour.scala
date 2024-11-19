package org.xuejiujiu.HiveToMySQL

import org.xuejiujiu.pack.connect.SparkHive

import java.util.Properties

object user_change_behavior_hour {
  def main(args: Array[String]): Unit = {
    val spark = SparkHive()
    val properties = new Properties()
    properties.setProperty("user","root")
    properties.setProperty("password","1234")
    properties.put("driver", "com.mysql.jdbc.Driver")


    val user_add = spark.sql(
      """
        |select
        |*
        |from
        |ads.user_change_behavior_hour where dates = 19
        |""".stripMargin)
    user_add.show()
    user_add.write.format("jdbc").mode("overwrite")
          // 指定写入字符集为UTF8，否则会乱码
          .option("url","jdbc:mysql://192.168.88.10:3306/comm?useUnicode=true&characterEncoding=utf8")
          .option("dbtable","user_change_behavior_hour")
          .option("user","root")
          .option("password","1234").save()
  }
}
