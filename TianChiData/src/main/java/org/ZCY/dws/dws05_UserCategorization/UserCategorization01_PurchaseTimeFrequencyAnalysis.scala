package org.ZCY.dws.dws05_UserCategorization

import org.xuejiujiu.pack.connect.SparkHive

object UserCategorization01_PurchaseTimeFrequencyAnalysis {
  def main(args: Array[String]): Unit = {
    val spark = SparkHive()
    // 每小时的用户行为变化
    println("----- 用户购买频率和时间的分析 -----")
    val user_change_behavior = spark.sql(
      """
        |SELECT user_id, DATEDIFF(MAX(date_format(to_timestamp(create_time), 'yyyy-MM-dd')), '2017-11-25') recency,
        |COUNT(behavior) frequency
        |FROM dwd.taobao_user_behavior
        |WHERE behavior = 'buy'
        |GROUP BY user_id
        |""".stripMargin)
    user_change_behavior.show()
    user_change_behavior.write.format("hive").mode("overwrite").saveAsTable("dws.user_recenvy_frequency")
    spark.table("dws.user_recenvy_frequency").show(false)
  }
}
