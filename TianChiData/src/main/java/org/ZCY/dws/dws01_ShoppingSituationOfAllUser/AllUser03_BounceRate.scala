package org.ZCY.dws.dws01_ShoppingSituationOfAllUser

import org.xuejiujiu.pack.connect.SparkHive

object AllUser03_BounceRate {
  def main(args: Array[String]): Unit = {
    val spark = SparkHive()
    // 每小时的用户行为变化
    println("----- 跳失率 -----")  // 1  pv*2 fav*1
    val user_change_behavior = spark.sql(
      """
        |SELECT
        |  CONCAT(
        |    CAST(ROUND((COUNT(DISTINCT user_id) * 100.0) / (SELECT COUNT(DISTINCT user_id) FROM dwd.taobao_user_behavior), 2) AS DECIMAL(10, 2)),
        |    '%'
        |  ) AS bounce_rate
        |FROM
        |  dwd.taobao_user_behavior
        |WHERE
        |  user_id NOT IN (SELECT DISTINCT user_id FROM dwd.taobao_user_behavior WHERE behavior = 'buy')
        |  AND user_id NOT IN (SELECT DISTINCT user_id FROM dwd.taobao_user_behavior WHERE behavior = 'cart')
        |  AND user_id NOT IN (SELECT DISTINCT user_id FROM dwd.taobao_user_behavior WHERE behavior = 'fav' )
        |
        |""".stripMargin)
    user_change_behavior.show()
    user_change_behavior.write.format("hive").mode("overwrite").saveAsTable("dws.Buonce_Rate")
    spark.table("dws.Buonce_Rate").show(false)
  }
}
