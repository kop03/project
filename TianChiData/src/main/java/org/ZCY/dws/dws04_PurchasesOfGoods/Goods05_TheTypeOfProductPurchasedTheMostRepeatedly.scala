package org.ZCY.dws.dws04_PurchasesOfGoods

import org.xuejiujiu.pack.connect.SparkHive

object Goods05_TheTypeOfProductPurchasedTheMostRepeatedly {
  def main(args: Array[String]): Unit = {
    val spark = SparkHive()
    // 每小时的用户行为变化
    println("----- 重复购买最多的商品的种类 -----")  // 1  pv*2 fav*1
    val user_change_behavior = spark.sql(
      """
        |SELECT cmd_type_id, COUNT(behavior) buy_cnt
        |FROM dwd.taobao_user_behavior
        |WHERE behavior = 'buy'
        |GROUP BY cmd_type_id
        |ORDER BY COUNT(behavior) DESC
        |LIMIT 10;
        |
        |""".stripMargin)
    user_change_behavior.show()
    user_change_behavior.write.format("hive").mode("overwrite").saveAsTable("dws.Type_Of_Product_Purchased_The_Most_Repeatedly")
    spark.table("dws.Type_Of_Product_Purchased_The_Most_Repeatedly").show(false)

  }
}
