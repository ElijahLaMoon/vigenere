package vigenere

import scala.annotation.tailrec
import scala.io.StdIn

object Main extends App {
  print("Enter your message: ")
  val plaintext = StdIn.readLine().trim

  print("Enter your key: ")
  val key = StdIn.readLine().trim

  print(s"Elongated key: ${Helpers.elongateKey(key, plaintext.length)}")
}

private object Helpers {
  def elongateKey(key: String, messageLength: Int): String = {

    if (key.length >= messageLength) key
    else {
      val charactersToAppendCount = messageLength - key.length

      @tailrec
      def elongateHelper(index: Int = 0, keyToElongate: String): String = {

        if (index.equals(charactersToAppendCount)) keyToElongate
        else {
          val newKey = keyToElongate.appended(key.charAt(index % key.length))
          elongateHelper(index + 1, newKey)
        }
      }

      elongateHelper(keyToElongate = key)
    }
  }
}