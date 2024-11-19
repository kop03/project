package org.ZCY.dws.dws02_Retention

import org.xuejiujiu.pack.connect.SparkHive

object Retention05_ConsolidatedRetentionRate {
  def main(args: Array[String]): Unit = {
    val spark = SparkHive()
    // 每小时的用户行为变化
    println("----- 留存率合并 -----")  // 1  pv*2 fav*1
//    val user_change_behavior = spark.sql(
//      """
//        -- 每日增长
//        |SELECT b.first_day, COUNT(DISTINCT b.user_id) day_increment,
//        |-- 次日留存率
//        |ROUND(100 * COUNT(DISTINCT IF(DATEDIFF(date_format(to_timestamp(create_time), 'yyyy-MM-dd'), b.first_day)=1, a.user_id, NULL)) / COUNT(DISTINCT b.user_id), 2)nextday_stay,
//        |-- 三日留存率
//        |ROUND(100 * COUNT(DISTINCT IF(DATEDIFF(date_format(to_timestamp(create_time), 'yyyy-MM-dd'), b.first_day)=3, a.user_id, NULL)) / COUNT(DISTINCT b.user_id), 2) three_days_stay,
//        |-- 五日留存率
//        |ROUND(100 * COUNT(DISTINCT IF(DATEDIFF(date_format(to_timestamp(create_time), 'yyyy-MM-dd'), b.first_day)=5, a.user_id, NULL)) / COUNT(DISTINCT b.user_id), 2) five_days_stay,
//        |-- 七日留存率合
//        |ROUND(100 * COUNT(DISTINCT IF(DATEDIFF(date_format(to_timestamp(create_time), 'yyyy-MM-dd'), b.first_day)=7, a.user_id, NULL)) / COUNT(DISTINCT b.user_id), 2) seven_days_stay
//        |FROM dwd.taobao_user_behavior a JOIN
//        |(
//        |SELECT user_id, MIN(date_format(to_timestamp(create_time), 'yyyy-MM-dd')) first_day
//        |FROM dwd.taobao_user_behavior
//        |GROUP BY user_id
//        |) b
//        |ON a.user_id = b.user_id
//        |GROUP BY b.first_day
//        |""".stripMargin)
//    user_change_behavior.show()

    val frame = spark.sql(
      """
        |select
        |a.first_day,
        |a.nextday_increment,
        |b.three_days_stay,
        |c.five_days_stay,
        |d.seven_days_stay
        |from
        |(select first_day,nextday_increment from dws.nextday_retenition_rate) a
        |left join
        |(select first_day,three_days_stay from dws.threeday_retenition_rate) b
        |on a.first_day = b.first_day
        |left join
        |(select first_day,five_days_stay from dws.fiveday_retenition_rate) c
        |on a.first_day = c.first_day
        |left join
        |(select first_day,seven_days_stay from dws.severnday_retenition_rate) d
        |on a.first_day = d.first_day
        |""".stripMargin)
    frame.show()
    frame.write.format("hive").mode("overwrite").saveAsTable("dws.Consoildate_Retention_Rate")
    spark.table("dws.Consoildate_Retention_Rate").show(false)
  }
}
