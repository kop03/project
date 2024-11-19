package org.ZCY.dws.dws05_UserCategorization

import org.xuejiujiu.pack.connect.SparkHive

object UserCategorization03_UserCategorization {
  def main(args: Array[String]): Unit = {
    val spark = SparkHive()
    println("----- 用户分类 -----")
    val user_change_behavior = spark.sql(
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
    user_change_behavior.show()
    user_change_behavior.write.format("hive").mode("overwrite").saveAsTable("dws.user_rank")
    spark.table("dws.user_rank").show(false)
  }
}
