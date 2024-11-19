package org.ZCY.dws.dws04_PurchasesOfGoods

import org.xuejiujiu.pack.connect.SparkHive

object Goods03_UsersWithMostRepeatPurchases {
  def main(args: Array[String]): Unit = {
    val spark = SparkHive()
    // 每小时的用户行为变化
    println("----- 重复购买最多的用户 -----")  // 1  pv*2 fav*1
    val user_change_behavior = spark.sql(
      """
       SELECT user_id, COUNT(behavior) buy_cnt
        |FROM dwd.taobao_user_behavior
        |WHERE behavior = 'buy'
        |GROUP BY user_id
        |ORDER BY COUNT(behavior) DESC
        |LIMIT 10;
        |
        |""".stripMargin)
    user_change_behavior.show()
    user_change_behavior.write.format("hive").mode("overwrite").saveAsTable("dws.Users_With_Most_Repeat_Purchases")
    spark.table("dws.Users_With_Most_Repeat_Purchases").show(false)
  }
}
