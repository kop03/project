package org.xuejiujiu.ods

import org.xuejiujiu.pack.connect.SparkHive

object ods_taobao_user_behavior {
  def main(args: Array[String]): Unit = {
    val spark = SparkHive()

    // TODO 创建数据库 Ods 层
    spark.sql("create database if not exists ods")
    spark.sql("use ods");
    // 1. 创建数据库Ods层的原始数据表
    spark.sql(
      """
        |CREATE TABLE if not exists taobao_user_behavior (
        |user_id       INT,
        |cmd_id       INT,
        |cmd_type_id  INT,
        |behavior     STRING,
        |create_time   BIGINT
        |)
        |ROW FORMAT DELIMITED
        |FIELDS TERMINATED BY ',';
        |""".stripMargin)
    // 2. 导入数据(建完表后,将数据导入到HDFS下)
    // hdfs://bigdata1:8020/user/hive/warehouse/ods.db/taobao_user_behavior/

    spark.sql("select * from ods.taobao_user_behavior").show(false)

    // 查询一共有多少条数据
    println("----- 查看数据条数 -----")
    val data_sumdf = spark.sql(
      """
        |SELECT
        |COUNT(*) `data_sum`
        |FROM
        |ods.taobao_user_behavior
        |""".stripMargin)
    data_sumdf.write.mode("overwrite").saveAsTable("ods.data_sum")
    spark.sql("select * from ods.data_sum").show(false)


  }
}
