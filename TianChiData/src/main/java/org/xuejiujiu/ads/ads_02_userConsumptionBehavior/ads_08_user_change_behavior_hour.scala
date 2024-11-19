package org.xuejiujiu.ads.ads_02_userConsumptionBehavior

import org.xuejiujiu.pack.connect.SparkHive

object ads_08_user_change_behavior_hour {
  def main(args: Array[String]): Unit = {
    val spark = SparkHive()
    // 每小时的用户行为变化
    println("----- 每小时用户行为变化 -----")  // 1  pv*2 fav*1
    val user_change_behavior = spark.sql(
      """
        |SELECT
        |date_format(to_timestamp(create_time), 'HH') dates,
        |COUNT(DISTINCT user_id) user_cnt,
        |COUNT(behavior) behavior_cnt,
        |SUM(CASE WHEN behavior='pv'   THEN 1 ELSE 0 END) pv_sum,
        |SUM(CASE WHEN behavior='fav'  THEN 1 ELSE 0 END) fav_sum,
        |SUM(CASE WHEN behavior='cart' THEN 1 ELSE 0 END) cart_sum,
        |SUM(CASE WHEN behavior='buy'  THEN 1 ELSE 0 END) buy_sum
        |FROM dwd.taobao_user_behavior
        |GROUP BY date_format(to_timestamp(create_time), 'HH')
        |ORDER BY dates
        |""".stripMargin)
    user_change_behavior.show()
    user_change_behavior.write.format("hive").mode("overwrite").saveAsTable("ads.user_change_behavior_hour")
    spark.table("ads.user_change_behavior_hour").show(false)
  }
}
