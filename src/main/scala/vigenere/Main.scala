package vigenere

import scala.annotation.tailrec
import scala.io.StdIn

object Main extends App {

  print("Enter your message: ")
  val plaintext = StdIn.readLine().trim.toUpperCase

  print("Enter your key: ")
  val key = StdIn.readLine().trim.toUpperCase

  println(s"Encrypted message: ${Helpers.encryptMessage(Helpers.elongateKey(key, plaintext.length), plaintext)}")
}

private object Helpers {

  private val indexedAlphabet = (0 to 25).zip('A' to 'Z').toMap

  def elongateKey(key: String, messageLength: Int): String = {

    if (key.length >= messageLength) key
    else {
      val charactersToAppendCount = messageLength - key.length

      @tailrec
      def elongateHelper(index: Int = 0, keyToElongate: String): String = {
        if (index == charactersToAppendCount) keyToElongate
        else {
          val newKey = keyToElongate.appended(key.charAt(index % key.length))
          elongateHelper(index + 1, newKey)
        }
      }

      elongateHelper(keyToElongate = key)
    }
  }

  def encryptMessage(key: String, message: String): String = {

    @tailrec
    def cipherHelper(index: Int = 0, ciphertext: String = ""): String = {

      if (index == message.length) ciphertext
      else {
        val encryptedChar = indexedAlphabet((message.charAt(index) + key.charAt(index)) % 26)
        cipherHelper(index + 1, ciphertext.appended(encryptedChar))
      }
    }

    cipherHelper()
  }
}