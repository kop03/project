package org.AnJuKeData.ods

import org.xuejiujiu.pack.connect.SparkHive

object ods {
  def main(args: Array[String]): Unit = {
    val spark = SparkHive()

    // TODO 创建数据库 Ods 层
    spark.sql("use ajkdata_ods;")
    // 创建ods原始表
//    spark.sql(
//      """
//        |CREATE EXTERNAL TABLE ods_data (
//        |`URL` STRING,
//        |`title` STRING,
//        |`area` STRING,
//        |`type` STRING,
//        |`direction` STRING,
//        |`floor_drop` STRING,
//        |`floor` STRING,
//        |`create_time` STRING,
//        |`neighborhood` STRING,
//        |`keyword` STRING,
//        |`price` STRING,
//        |`Average` STRING
//        |)
//        |ROW FORMAT DELIMITED
//        |FIELDS TERMINATED BY ','
//        |STORED AS TEXTFILE
//        |location '/user/hive/warehouse/ajkdata_ods.db/ods_data';
//        |""".stripMargin)

//    spark.sql(
//      """
//        |LOAD DATA INPATH '/data.csv' overwrite INTO TABLE
//        |ajkdata_ods.ods_data
//        |""".stripMargin)

    spark.sql(
      """
        |select
        |count(*)
        |from
        |ods_data
        |""".stripMargin).show()

    val drop_repeat = spark.sql(
      """
        |select
        |distinct *
        |from
        |ods_data
        |""".stripMargin)
    drop_repeat.show()

    drop_repeat.createTempView("drop_repeat_table")

    val drop_null = spark.sql(
      """
        |select
        |*
        |from
        |drop_repeat_table
        |where
        |URL is not null
        |or
        |title is not null
        |or
        |area is not null
        |or
        |type is not null
        |or
        |direction is not null
        |or
        |floor is not null
        |or
        |create_time is not null
        |or
        |neighborhood is not null
        |or
        |keyword is not null
        |or
        |price is not null
        |or
        |average is not null
        |
        |""".stripMargin)
    drop_null.show()
    drop_null.createTempView("drop_null_table")
    spark.sql(
      """
        |select
        |count(*)
        |from
        |drop_null_table
        |""".stripMargin).show()
  }
}
