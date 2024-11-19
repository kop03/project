package org.ZCY.dws.dws01_ShoppingSituationOfAllUser

import org.xuejiujiu.pack.connect.SparkHive

object AllUser02_AverageVisits {
  def main(args: Array[String]): Unit = {
    val spark = SparkHive()
    // 每小时的用户行为变化
    println("----- 平均访问量 -----")  // 1  pv*2 fav*1
    val user_change_behavior = spark.sql(
      """
        |SELECT COUNT(DISTINCT user_id) UV,
        |(SELECT COUNT(behavior) FROM dwd.taobao_user_behavior WHERE behavior = 'pv') PV,
        |(SELECT COUNT(behavior) FROM dwd.taobao_user_behavior WHERE behavior = 'pv')/COUNT(DISTINCT user_id) PV_UV
        |FROM dwd.taobao_user_behavior
        |
        |""".stripMargin)
    user_change_behavior.show()
    user_change_behavior.write.format("hive").mode("overwrite").saveAsTable("dws.Average_visits")
    spark.table("dws.Average_visits").show(false)
  }
}
