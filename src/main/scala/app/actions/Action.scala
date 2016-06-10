package app.actions

trait Action

case object UnknownOperation extends Action

case object Logout extends Action

case class Login(username: String, password: String) extends Action

case class AddItem(item: String, amount: Int) extends Action

case class BuyItem(item: String, amount: Int) extends Action
