package org.ZCY

import java.time.LocalDate
import java.time.format.DateTimeFormatter

object date {
  def main(args: Array[String]): Unit = {

    // 获取当前日期
    val currentDate = LocalDate.now()

    // 定义日期格式化器
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

    // 格式化当前日期
    val formattedDate = currentDate.format(formatter)

    // 打印格式化后的日期
    println(formattedDate)


  }
}
