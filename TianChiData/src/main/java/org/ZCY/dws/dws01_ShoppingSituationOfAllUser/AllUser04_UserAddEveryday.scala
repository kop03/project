package org.ZCY.dws.dws01_ShoppingSituationOfAllUser

import org.xuejiujiu.pack.connect.SparkHive

object AllUser04_UserAddEveryday {
  def main(args: Array[String]): Unit = {
    val spark = SparkHive()
    // 每小时的用户行为变化
    println("----- 每日用户新增数 -----")  // 1  pv*2 fav*1
    val user_change_behavior = spark.sql(
      """
        |SELECT b.first_day, COUNT(DISTINCT b.user_id) day_increment
        |FROM dwd.taobao_user_behavior a JOIN
        |(
        |SELECT user_id, MIN(date_format(to_timestamp(create_time), 'yyyy-MM-dd')) first_day
        |FROM dwd.taobao_user_behavior
        |GROUP BY user_id
        |) b
        |ON a.user_id = b.user_id
        |GROUP BY b.first_day
        |Order by b.first_day
        |
        |""".stripMargin)
    user_change_behavior.show()
    user_change_behavior.write.format("hive").mode("overwrite").saveAsTable("dws.user_add_everyday")
    spark.table("dws.user_add_everyday").show(false)
  }
}
