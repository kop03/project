package org.ZCY.dws.dws05_UserCategorization

import org.xuejiujiu.pack.connect.SparkHive

object UserCategorization02_DivideTheIntervalLevel {
  def main(args: Array[String]): Unit = {
    val spark = SparkHive()
    println("----- 划分区间等级 -----")
    val user_change_behavior = spark.sql(
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
        |""".stripMargin)
    user_change_behavior.show()
    user_change_behavior.write.format("hive").mode("overwrite").saveAsTable("dws.user_score")
    spark.table("dws.user_score").show(false)
  }
}
