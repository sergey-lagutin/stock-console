package app.service

import scala.collection.mutable.HashMap

private[service] object Stock {
  val stock = HashMap(
    "item1" -> 10
  )
}

class StockService {

  import Stock.stock

  def listAll: Seq[(String, Int)] =
    stock.toSeq.sortBy(_._1)

  def buy(item: String, amount: Int): Either[Int, String] = {
    stock.get(item) match {
      case Some(stockAmount) if stockAmount >= amount =>
        val newAmount = stockAmount - amount
        stock(item) = newAmount
        Left(newAmount)
      case None => Right(s"Item [$item] not found")
      case _ => Right("Insufficient amount")
    }
  }

  def add(item: String, amount: Int): Int = {
    val oldAmount = stock.getOrElse(item, 0)
    val newAmount = oldAmount + amount
    stock(item) = newAmount
    newAmount
  }
}
