package org.ZCY.dws.dws04_PurchasesOfGoods

import org.xuejiujiu.pack.connect.SparkHive

object Goods04_TheMostRepeatPurchases {
  def main(args: Array[String]): Unit = {
    val spark = SparkHive()
    // 每小时的用户行为变化
    println("----- 重复购买最多的商品 -----")  // 1  pv*2 fav*1
    val user_change_behavior = spark.sql(
      """
        |SELECT cmd_id, COUNT(behavior) buy_cnt
        |FROM dwd.taobao_user_behavior
        |WHERE behavior = 'buy'
        |GROUP BY cmd_id
        |ORDER BY COUNT(behavior) DESC
        |LIMIT 10
        |""".stripMargin)
    user_change_behavior.show()
    user_change_behavior.write.format("hive").mode("overwrite").saveAsTable("dws.The_Most_Repeat_Purchases")
    spark.table("dws.The_Most_Repeat_Purchases").show(false)
  }
}
