package org.ZCY.dws.dws02_Retention

import org.xuejiujiu.pack.connect.SparkHive

object Retention01_NextdayRetentionRate {
  def main(args: Array[String]): Unit = {
    val spark = SparkHive()
    // 每小时的用户行为变化
    println("----- 次日留存率 -----")  // 1  pv*2 fav*1
    val user_change_behavior = spark.sql(
      """
        SELECT b.first_day, CONCAT(ROUND(100 * COUNT(DISTINCT IF(DATEDIFF(date_format(to_timestamp(create_time), 'yyyy-MM-dd')
        |, b.first_day)=1, a.user_id, NULL)) / COUNT(DISTINCT b.user_id), 2), '%') nextday_increment
        |FROM dwd.taobao_user_behavior a JOIN
        |(
        |SELECT user_id, MIN(date_format(to_timestamp(create_time), 'yyyy-MM-dd')) first_day
        |FROM dwd.taobao_user_behavior
        |GROUP BY user_id
        |) b
        |ON a.user_id = b.user_id
        |GROUP BY b.first_day
        |
        |
        |""".stripMargin)
    user_change_behavior.show()
    user_change_behavior.write.format("hive").mode("overwrite").saveAsTable("dws.nextday_retenition_rate")
    spark.table("dws.nextday_retenition_rate").show(false)
  }
}
