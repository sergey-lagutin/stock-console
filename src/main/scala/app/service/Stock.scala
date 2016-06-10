package app.service

class Stock(stock: Map[String, Int]) {
  def buy(item: String, amount: Int): Stock =
    stock.get(item) match {
      case Some(stockAmount) if stockAmount >= amount =>
        new Stock(stock + (item -> (stockAmount - amount)))
      case _ =>
        println("Insufficient amount")
        new Stock(stock)
    }

  def add(item: String, amount: Int): Stock =
    new Stock(stock + (item -> (stock.getOrElse(item, 0) + amount)))

  def getAmount(item: String): Int = stock.getOrElse(item, 0)

  def showAll() = {
    println("Current items in stock:")
    stock.toSeq.foreach {
      case (item, amount) => println(s"$item: $amount pcs.")
    }
  }
}
