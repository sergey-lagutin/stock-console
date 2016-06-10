package app.actions

object StockActions {

  def add(): Action =
    readItemAndAmount.map {
      case (item, amount) => AddItem(item, amount)
    }.getOrElse(UnknownOperation)

  def buy(): Action =
    readItemAndAmount.map {
      case (item, amount) => BuyItem(item, amount)
    }.getOrElse(UnknownOperation)

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

}
