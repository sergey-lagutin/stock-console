package app.service

import scala.collection.mutable.HashMap
import Stock.stock

private[service] object Stock {
  val stock = HashMap(
    "item1" -> 10
  )
}

class StockService {

  def listAll: Seq[(String, Int)] = {
    val tuples = stock.toSeq
    tuples.sortBy(_._1)
  }

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
