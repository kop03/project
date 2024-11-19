package org.ZCY.dws.dws01_ShoppingSituationOfAllUser

import org.xuejiujiu.pack.connect.SparkHive

object AllUser01_OverallStatistics {
  def main(args: Array[String]): Unit = {
    val spark = SparkHive()
    // 每小时的用户行为变化
    println("----- 整体统计 -----")  // 1  pv*2 fav*1
    val taobao_user_behavior = spark.sql(
      """
      SELECT COUNT(*) lines,
        |COUNT(DISTINCT user_id) users,
        |COUNT(DISTINCT cmd_id) cmd_cnt,
        |COUNT(DISTINCT cmd_type_id) cmd_type_cnt
        |FROM dwd.taobao_user_behavior
        |
        |""".stripMargin)
    taobao_user_behavior.show()
    taobao_user_behavior.write.format("hive").mode("overwrite").saveAsTable("dws.overall_statistics")
    spark.table("dws.overall_statistics").show(false)
  }
}
