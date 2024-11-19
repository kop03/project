package org.ZCY.dws.dws04_PurchasesOfGoods

import org.xuejiujiu.pack.connect.SparkHive

object Goods01_TheNumberOfPurchasesPerson {
  def main(args: Array[String]): Unit = {
    val spark = SparkHive()
    // 每小时的用户行为变化
    println("----- 人均购买次数 -----")  // 1  pv*2 fav*1
    val user_change_behavior = spark.sql(
      """
        |SELECT COUNT(behavior) behavior_cnt, COUNT(DISTINCT user_id) uesr_cnt,
        |COUNT(behavior) / COUNT(DISTINCT user_id) avg_buy
        |FROM dwd.taobao_user_behavior
        |WHERE behavior = 'buy';
        |
        |""".stripMargin)
    user_change_behavior.show()
    user_change_behavior.write.format("hive").mode("overwrite").saveAsTable("dws.The_Number_Of_Purchases_Person")
    spark.table("dws.The_Number_Of_Purchases_Person").show(false)
  }
}
