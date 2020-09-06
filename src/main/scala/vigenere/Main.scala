package vigenere

import scala.io.StdIn

object Main extends App {
  print("Enter your message: ")
  val plaintext = StdIn.readLine().trim.toUpperCase

  print("Enter your key: ")
  val key = StdIn.readLine().trim.toUpperCase

  println(s"Encrypted message: ${Helpers.encryptMessage(Helpers.elongateKey(key, plaintext.length), plaintext)}")
  println(s"Decrypted message: ${Helpers.decryptMessage(Helpers.elongateKey(key, plaintext.length), plaintext)}")
}