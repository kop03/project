package org.xuejiujiu.dws.dws_01_allUserShopStatus

import org.xuejiujiu.pack.connect.SparkHive

object dws_02_user_nextday_stay {
  def main(args: Array[String]): Unit = {
    var spark = SparkHive()
    /*
      留存率：
          使用群组分析方法，按每天新增的用户进行分组，通过每天新增用户的留存率来判断平台对用户的吸引力。
     */
    // TODO 次日留存率
    // 思路：找到每个用户最早的行为时间，然后进行交叉查询
    println("----- 次日留存率 -----")
    val user_add_everydaydf = spark.sql(
      """
        |SELECT
        |b.first_day,
        |ROUND(100 * COUNT(DISTINCT IF(DATEDIFF(date_format(to_timestamp(create_time), 'yyyy-MM-dd'), b.first_day)=1, a.user_id, NULL)) / COUNT(DISTINCT b.user_id), 2) nextday_increment
        |FROM
        |dwd.taobao_user_behavior a JOIN
        |(
        |SELECT
        |user_id,
        |MIN(date_format(to_timestamp(create_time), 'yyyy-MM-dd')) first_day
        |FROM
        |dwd.taobao_user_behavior
        |GROUP BY user_id
        |) b
        |ON a.user_id = b.user_id
        |GROUP BY b.first_day
        |order by b.first_day
        |""".stripMargin)
    user_add_everydaydf.show()
    user_add_everydaydf.write.format("hive").mode("overwrite").saveAsTable("dws.nextday_stay")
    spark.table("dws.nextday_stay").show()
  }
}
