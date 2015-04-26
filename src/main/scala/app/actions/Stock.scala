package app.actions

import app.service.StockService

object Stock {

  private val service = new StockService

  def deposit(): Unit = {
    readItemAndAmount.foreach {
      case (item, amount) =>
        service.deposit(item, amount)
    }
  }

  def buy(): Unit = {
    readItemAndAmount.foreach {
      case (item, amount) =>
        service.buy(item, amount) match {
          case Left(leftAmount) =>
            println(s"Item [$item] was withdrawn. Left $leftAmount pcs.")
          case Right(error) =>
            println("Cannot withdraw: " + error)
        }
    }
  }

  private val itemAndAmountRegex = "(\\w+)\\s+(\\d+)".r

  private def readItemAndAmount: Option[(String, Int)] = {
    val itemAmount = readString("Input type of item and amount (string and number separated by space)")
    itemAmount.trim match {
      case itemAndAmountRegex(item, amount) =>
        Some((item, amount.toInt))
      case _ =>
        println("Incorrect string format. Expected [ITEM_TYPE AMOUNT]")
        None
    }
  }

  def showAll() = {
    println("Current items in stock:")
    service.listAll.foreach {
      case (item, amount) => println(s"$item: $amount pcs.")
    }
  }

}
