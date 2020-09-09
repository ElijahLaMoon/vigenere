package vigenere

import scala.io.StdIn

object Main extends App {
  print("Enter your message: ")
  val plaintext = StdIn.readLine().trim.toUpperCase

  print("Enter your key: ")
  val key = Helpers.elongateKey(StdIn.readLine().trim.toUpperCase, plaintext.length)

  println(s"Encrypted message: ${Helpers.encryptMessage(key, plaintext)}")
  println(s"Decrypted message: ${Helpers.decryptMessage(key, plaintext)}")
}
