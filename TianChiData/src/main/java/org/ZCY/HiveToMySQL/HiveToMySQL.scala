package org.ZCY.HiveToMySQL

import org.xuejiujiu.pack.connect.SparkHive

import java.util.Properties

object HiveToMySQL {
  def main(args: Array[String]): Unit = {
    val spark = SparkHive()
    val properties = new Properties()
    properties.setProperty("user","root")
    properties.setProperty("password","1234")
    properties.put("driver", "com.mysql.jdbc.Driver")
    // 获取所有表
    spark.sql("USE dws")
    val tablesDF = spark.sql("SHOW TABLES")

    // 将结果收集为数组
    val table = tablesDF.collect().map(_.getString(1))
    table.foreach(tableName => {
      println(tableName)
    })
    // 打印表列表
    table.foreach(tableName => {
      // 将数组中的表依次写入
      println(s"----- $tableName -----")
      if(tableName.equals("user_rank") || tableName.equals("user_recenvy_frequency") || tableName.equals("user_score")){
        // 由于这个表太大，所以只抽取前500行进入MySQL中
        val user_add = spark.sql(
          s"""
             |select
             |*
             |from
             |dws.$tableName
             |limit 500
             |""".stripMargin)
        user_add.show()
        user_add.write.format("jdbc").mode("overwrite")
          // 指定写入字符集为UTF8，否则会乱码
          .option("url","jdbc:mysql://192.168.88.10:3306/comm?useUnicode=true&characterEncoding=utf8")
          .option("dbtable",s"$tableName")
          .option("user","root")
          .option("password","1234").save()
        println(s"----- ${tableName} 写入成功 -----")
      }else{
        val user_add = spark.sql(
        s"""
           |select
           |*
           |from
           |dws.$tableName
           |""".stripMargin)
        user_add.show()
        user_add.write.format("jdbc").mode("overwrite")
          // 指定写入字符集为UTF8，否则会乱码
          .option("url","jdbc:mysql://192.168.88.10:3306/comm?useUnicode=true&characterEncoding=utf8")
          .option("dbtable",s"$tableName")
          .option("user","root")
          .option("password","1234").save()
        println(s"----- ${tableName} 写入成功 -----")
      }
    })

  }
}
