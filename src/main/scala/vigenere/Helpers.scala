package vigenere

import scala.annotation.tailrec

object Helpers {
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

  def decryptMessage(key: String, message: String): String = {
    @tailrec
    def decipherHelper(index: Int = 0, plaintext: String = ""): String = {
      if (index == message.length) plaintext
      else {
        val decryptedChar = indexedAlphabet(((message.charAt(index) - key.charAt(index)) + 26) % 26)
        decipherHelper(index + 1, plaintext.appended(decryptedChar))
      }
    }

    decipherHelper()
  }
}
