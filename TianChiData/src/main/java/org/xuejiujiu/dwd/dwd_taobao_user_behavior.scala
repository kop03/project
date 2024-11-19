package org.xuejiujiu.dwd

import org.xuejiujiu.pack.connect.SparkHive

object dwd_taobao_user_behavior {
  def main(args: Array[String]): Unit = {
    val spark = SparkHive()
    // TODO 清洗原始数据,存入dwd层
    // 查看有效数据
    println("----- 查看有效数据 -----")
    val data_effectivedf = spark.sql(
      """
        |SELECT COUNT(*) lines
        |FROM ods.taobao_user_behavior
        |WHERE date_format(to_timestamp(create_time), 'yyyy-MM-dd') >= '2017-11-25'
        |AND date_format(to_timestamp(create_time), 'yyyy-MM-dd') <= '2017-12-03'
        |""".stripMargin)
    data_effectivedf.write.mode("overwrite").saveAsTable("dwd.data_effective")
    spark.sql("select * from dwd.data_effective").show(false)

    // 查看有效数据占比
    println("----- 有效数据占比 -----")  //  有效数据 / 实际总数据 *100
    val data_percentdf = spark.sql(
      """
        |SELECT
        |concat(
        |   round(
        |       (SELECT lines FROM dwd.data_effective)/(SELECT data_sum FROM ods.data_sum)
        |       * 100, 2)
        |   , '%')
        |as result
  """.stripMargin)
    data_percentdf.write.mode("overwrite").saveAsTable("dwd.data_percent")
    spark.sql("select * from dwd.data_percent").show(false)



    // 数据清洗，将有效数据创建为表
    println("----- 清洗数据并存入表格 -----")
    val taobao_user_behavior = spark.sql(
      """
        |SELECT
        |user_id,
        |cmd_id,
        |cmd_type_id,
        |behavior,
        |create_time
        |FROM
        |ods.taobao_user_behavior
        |WHERE
        |date_format(to_timestamp(create_time), 'yyyy-MM-dd') >= '2017-11-25'
        |AND
        |date_format(to_timestamp(create_time), 'yyyy-MM-dd') <= '2017-12-03'
        |""".stripMargin)
    taobao_user_behavior.write.mode("overwrite").saveAsTable("dwd.taobao_user_behavior")
    spark.table("dwd.taobao_user_behavior").show(false)
  }
}
