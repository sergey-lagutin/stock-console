package app.service

import org.scalatest.FunSuite

class StockServiceTest extends FunSuite {

  trait EmptyStock extends StockService {
    Stock.stock.clear()
  }

  test("add new item") {
    new EmptyStock {
      assert(10 === add("item", 10))
    }
  }

  test("add existent item") {
    new EmptyStock {
      add("item", 10)
      assert(15 === add("item", 5))
    }
  }

  test("buy unreal item") {
    new EmptyStock {
      assert(Right("Item [item] not found") === buy("item", 10))
    }
  }

  test("buy with insufficient amount") {
    new EmptyStock {
      add("item", 10)
      assert(Right("Insufficient amount") === buy("item", 20))
    }
  }

  test("buy") {
    new EmptyStock {
      add("item", 10)
      assert(Left(8) === buy("item", 2))
    }
  }

  test("listAll") {
    new EmptyStock {
      add("item2", 20)
      add("item3", 15)
      add("item1", 10)

      val expected = Seq(
        ("item1", 10),
        ("item2", 20),
        ("item3", 15)
      )

      assert(expected === listAll)
    }
  }

}
