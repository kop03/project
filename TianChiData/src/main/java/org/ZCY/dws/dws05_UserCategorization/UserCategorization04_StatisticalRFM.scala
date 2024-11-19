package org.ZCY.dws.dws05_UserCategorization

import org.xuejiujiu.pack.connect.SparkHive

object UserCategorization04_StatisticalRFM {
  def main(args: Array[String]): Unit = {
    val spark = SparkHive()
    println("----- RFM统计 -----")
    val user_change_behavior = spark.sql(
      """
        SELECT
        |user_rank,
        |COUNT(user_id) AS user_cnt
        |FROM dws.user_rank
        |GROUP BY user_rank
        |""".stripMargin)
    // 过滤掉空值行
    val filtered_data = user_change_behavior.filter(user_change_behavior("user_rank").isNotNull)
    filtered_data.show()
    filtered_data.write.format("hive").mode("overwrite").saveAsTable("dws.RFM_UserType")
    spark.table("dws.RFM_UserType").show(false)
  }
}
