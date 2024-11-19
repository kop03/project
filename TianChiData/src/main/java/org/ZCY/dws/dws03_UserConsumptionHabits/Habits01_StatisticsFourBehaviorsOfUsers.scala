package org.ZCY.dws.dws03_UserConsumptionHabits

import org.xuejiujiu.pack.connect.SparkHive

object Habits01_StatisticsFourBehaviorsOfUsers {
  def main(args: Array[String]): Unit = {
    val spark = SparkHive()
    // 每小时的用户行为变化
    println("----- 用户四种行为的统计 -----")  // 1  pv*2 fav*1

    val frame = spark.sql(
      """
        |SELECT behavior, COUNT(*) cnt
        |FROM dwd.taobao_user_behavior
        |GROUP BY behavior
        |""".stripMargin)
    frame.show()
    frame.write.format("hive").mode("overwrite").saveAsTable("dws.statistics_Four_Behaviors_Of_Users")
    spark.table("dws.statistics_Four_Behaviors_Of_Users").show(false)
  }
}
