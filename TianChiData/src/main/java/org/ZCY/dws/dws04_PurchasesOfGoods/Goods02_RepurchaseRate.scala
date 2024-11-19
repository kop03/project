package org.ZCY.dws.dws04_PurchasesOfGoods

import org.xuejiujiu.pack.connect.SparkHive

object Goods02_RepurchaseRate {
  def main(args: Array[String]): Unit = {
    val spark = SparkHive()
    // 每小时的用户行为变化
    println("----- 复购率 -----")  // 1  pv*2 fav*1
    val user_change_behavior = spark.sql(
      """
        |SELECT SUM(CASE WHEN a.buy_sum >= 2 THEN 1 ELSE 0 END) /
        |SUM(CASE WHEN a.buy_sum > 0 THEN 1 ELSE 0 END) buy_again
        |FROM (
        |SELECT user_id,
        |SUM(CASE WHEN behavior='buy' THEN 1 ELSE 0 END) buy_sum
        |FROM dwd.taobao_user_behavior
        |GROUP BY user_id
        |) a;
        |""".stripMargin)
    user_change_behavior.show()
    user_change_behavior.write.format("hive").mode("overwrite").saveAsTable("dws.Repurchase_Rate")
    spark.table("dws.Repurchase_Rate").show(false)
  }
}
