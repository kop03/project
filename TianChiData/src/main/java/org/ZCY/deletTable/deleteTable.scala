package org.ZCY.deletTable

import org.xuejiujiu.pack.connect.SparkHive

object deleteTable {
  def main(args: Array[String]): Unit = {
    val spark = SparkHive()
    println("----- Ods层数据 -----")
    spark.sql("""use ods""")
    spark.sql("""show tables;""").show()

    // TODO 删除Dwd层数据
    println("----- Dwd层数据 -----")
    spark.sql("""use dwd""")

    spark.sql(
      """
        |drop table if exists dwd.data_effective
        |""".stripMargin)

    spark.sql(
      """
        |drop table if exists dwd.data_effectivedf
        |""".stripMargin)

    spark.sql(
      """
        |drop table if exists dwd.data_percent
        |""".stripMargin)

    spark.sql(
      """
        |drop table if exists dwd.taobao_user_behavior
        |""".stripMargin)

    spark.sql("""show tables;""").show()

    // TODO 删除Dsd层数据
    println("----- Dws层数据 -----")
    // 获取所有表
    spark.sql("USE dws")
    val tablesDF = spark.sql("SHOW TABLES")

    // 将结果收集为数组
    val table = tablesDF.collect().map(_.getString(1))

    // 打印表列表
    table.foreach(tableName => {
      spark.sql(
        s"""
          |drop table if exists dws.$tableName""".stripMargin)
      })
    spark.sql("""show tables;""").show()

  }
}
