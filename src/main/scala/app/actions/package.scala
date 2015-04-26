package app

package object actions {

  def readString(prompt: String): String = {
    print(prompt)
    Console.flush()
    Console.readLine()
  }
}
