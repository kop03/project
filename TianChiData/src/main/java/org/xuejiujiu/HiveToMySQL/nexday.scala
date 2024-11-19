package org.xuejiujiu.HiveToMySQL

import org.xuejiujiu.pack.connect.SparkHive

import java.util.Properties

object nexday {
  def main(args: Array[String]): Unit = {
    val spark = SparkHive()
    val properties = new Properties()
    properties.setProperty("user","root")
    properties.setProperty("password","1234")
    properties.put("driver", "com.mysql.jdbc.Driver")


    val user_add = spark.sql(
      """
        |select
        |first_day as name ,
        |nextday_increment as value
        |from
        |dws.nextday_stay
        |""".stripMargin)
    user_add.show()
    user_add.write.format("jdbc").mode("overwrite")
          // 指定写入字符集为UTF8，否则会乱码
          .option("url","jdbc:mysql://192.168.88.10:3306/comm?useUnicode=true&characterEncoding=utf8")
          .option("dbtable","nextday_stay")
          .option("user","root")
          .option("password","1234").save()
println("------ 写入 ------")
    val user_add2 = spark.sql(
      """
        |select
        |user_rank as name,
        |user_cnt as value
        |from
        |ads.statistics_all_user_type
        |""".stripMargin)
    user_add2.show()
    user_add2.write.format("jdbc").mode("overwrite")
      // 指定写入字符集为UTF8，否则会乱码
      .option("url","jdbc:mysql://192.168.88.10:3306/comm?useUnicode=true&characterEncoding=utf8")
      .option("dbtable","statistics_all_user_type")
      .option("user","root")
      .option("password","1234").save()
    println("------ 写入 ------")

    val user_add3 = spark.sql(
      """
        |select
        |first_day as name,
        |day_increment as value
        |from
        |dws.user_add_everyday
        |""".stripMargin)
    user_add3.show()
    user_add3.write.format("jdbc").mode("overwrite")
      // 指定写入字符集为UTF8，否则会乱码
      .option("url","jdbc:mysql://192.168.88.10:3306/comm?useUnicode=true&characterEncoding=utf8")
      .option("dbtable","user_add_everyday")
      .option("user","root")
      .option("password","1234").save()
    println("------ 写入 ------")

    val user_add4 = spark.sql(
      """
        |select
        |*
        |from
        |ads.user_change_behavior_hour where dates = 19
        |""".stripMargin)
    user_add4.show()
    user_add4.write.format("jdbc").mode("overwrite")
      // 指定写入字符集为UTF8，否则会乱码
      .option("url","jdbc:mysql://192.168.88.10:3306/comm?useUnicode=true&characterEncoding=utf8")
      .option("dbtable","user_change_behavior_hour")
      .option("user","root")
      .option("password","1234").save()
    println("------ 写入 ------")

  }
}
