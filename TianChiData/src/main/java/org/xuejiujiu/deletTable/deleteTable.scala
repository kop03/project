package org.xuejiujiu.deletTable

import org.xuejiujiu.pack.connect.SparkHive

object deleteTable {
  def main(args: Array[String]): Unit = {
    val spark = SparkHive()
    println("----- Ods层数据 -----")
    spark.sql("""use ods""")
    spark.sql("""show tables;""").show()

    // TODO 删除Dwd层数据
    println("----- Dwd层数据 -----")
    spark.sql("""use dwd""")

    spark.sql(
      """
        |drop table if exists dwd.data_effective
        |""".stripMargin)

    spark.sql(
      """
        |drop table if exists dwd.data_effectivedf
        |""".stripMargin)

    spark.sql(
      """
        |drop table if exists dwd.data_percent
        |""".stripMargin)

    spark.sql(
      """
        |drop table if exists dwd.taobao_user_behavior
        |""".stripMargin)

    spark.sql("""show tables;""").show()

    // TODO 删除Dsd层数据
    println("----- Dws层数据 -----")
    spark.sql("""use dws""")

    spark.sql(
      """
        |drop table if exists dws.average_visits
        |""".stripMargin)

    spark.sql(
      """
        |drop table if exists dws.buoncerate
        |""".stripMargin)

    spark.sql(
      """
        |drop table if exists dws.consoildateretentionrate
        |""".stripMargin)

    spark.sql(
      """
        |drop table if exists dws.fiveday_retenition_rate
        |""".stripMargin)

    spark.sql(
      """
        |drop table if exists dws.nextday_retenition_rate
        |""".stripMargin)

    spark.sql(
      """
        |drop table if exists dws.overall_statistics
        |""".stripMargin)

    spark.sql(
      """
        |drop table if exists dws.repurchaserate
        |""".stripMargin)

    spark.sql(
      """
        |drop table if exists dws.rfm_usertype
        |""".stripMargin)

    spark.sql(
      """
        |drop table if exists dws.severnday_retenition_rate
        |""".stripMargin)

    spark.sql(
      """
        |drop table if exists dws.statisticsfourbehaviorsofusers
        |""".stripMargin)

    spark.sql(
      """
        |drop table if exists dws.themostrepeatpurchases
        |""".stripMargin)

    spark.sql(
      """
        |drop table if exists dws.thenumberofpurchasesperson
        |""".stripMargin)

    spark.sql(
      """
        |drop table if exists dws.threeday_retenition_rate
        |""".stripMargin)

    spark.sql(
      """
        |drop table if exists dws.typeofproductpurchasedthemostrepeatedly
        |""".stripMargin)

    spark.sql(
      """
        |drop table if exists dws.userbehaviorschangehour
        |""".stripMargin)

    spark.sql(
      """
        |drop table if exists dws.userswithmostrepeatpurchases
        |""".stripMargin)
    spark.sql("""show tables;""").show()



  }
}
