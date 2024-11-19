package org.xuejiujiu.ads.ads_05_RFM

import org.xuejiujiu.pack.connect.SparkHive

object ads_28_statistics_all_user_type {
  def main(args: Array[String]): Unit = {
    val spark = SparkHive()
    println("----- 单个用户行为分析 -----")
    val singleUser_behaviordf = spark.sql(
      """
        SELECT user_id, DATEDIFF(MAX(date_format(to_timestamp(create_time), 'yyyy-MM-dd')), '2017-11-25') recency,
        |COUNT(behavior) frequency
        |FROM dwd.taobao_user_behavior
        |WHERE behavior = 'buy'
        |GROUP BY user_id
        |""".stripMargin)
    singleUser_behaviordf.write.format("hive").mode("overwrite").saveAsTable("dws.user_recenvy_frequency")
    spark.table("dws.user_recenvy_frequency").show()

    val singleUser_behaviordf2 = spark.sql(
      """
        |SELECT rf.*,
        |(
        |CASE
        |WHEN recency BETWEEN 0 AND 2 THEN 3
        |WHEN recency BETWEEN 3 AND 5 THEN 2
        |WHEN recency BETWEEN 6 AND 8 THEN 1
        |END
        |) r_score, (
        |CASE
        |WHEN frequency BETWEEN 0 AND 1   THEN 1
        |WHEN frequency BETWEEN 2 AND 3   THEN 2
        |WHEN frequency BETWEEN 4 AND 6   THEN 3
        |WHEN frequency BETWEEN 7 AND 159 THEN 4
        |END
        |) f_score
        |FROM dws.user_recenvy_frequency rf
        |
        |
        |""".stripMargin)
    singleUser_behaviordf2.write.format("hive").mode("overwrite").saveAsTable("dws.user_score")
    spark.table("dws.user_score").show()

    val singleUser_behaviordf3 = spark.sql(
      """
        |SELECT user_id, (
        |CASE
        |
        |WHEN us.r_score >= 3 AND us.f_score >= 4 THEN 'first_rank'
        |
        |WHEN us.r_score >= 3 AND us.f_score >= 3 THEN 'second_rank'
        |
        |WHEN us.r_score >= 2 AND us.f_score >= 2 THEN 'third_rank'
        |
        |WHEN us.r_score >= 1 AND us.f_score >= 1 THEN 'forth_rank'
        |END
        |) user_rank
        |FROM dws.user_score us
        |""".stripMargin)
    singleUser_behaviordf3.write.format("hive").mode("overwrite").saveAsTable("dws.user_rank")
    spark.table("dws.user_rank").show()

    println("----- 对各类用户进行统计 -----")
    val last_shopping_time = spark.sql(
      """
        |SELECT
        |user_rank,
        |COUNT(user_id) AS user_cnt
        |FROM dws.user_rank
        |GROUP BY user_rank
        |""".stripMargin)
    last_shopping_time.show()
    last_shopping_time.write.format("hive").mode("overwrite").saveAsTable("ads.statistics_all_user_type")
    spark.table("ads.statistics_all_user_type").show()

  }
}
