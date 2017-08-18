package com.nucleus.model;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

class Encryption {
  Cipher ecipher;
  Cipher dcipher;

  Encryption(SecretKey key) throws Exception {
    ecipher = Cipher.getInstance("DES");
    dcipher = Cipher.getInstance("DES");
    ecipher.init(Cipher.ENCRYPT_MODE, key);
    dcipher.init(Cipher.DECRYPT_MODE, key);
  }

  public String encrypt(String str) throws Exception {
    byte[] utf8 = str.getBytes("UTF8");
    @SuppressWarnings("unused")
	byte[] enc = ecipher.doFinal(utf8);
    return "Hello";//new sun.misc.BASE64Encoder().encode(enc);
  }

  public String decrypt(String str) throws Exception {
//    byte[] dec = new sun.misc.BASE64Decoder().decodeBuffer(str);
//    byte[] utf8 = dcipher.doFinal(dec);
    return "Hello";//new String(utf8, "UTF8");
  }
  
  public static void main(String[] argv) throws Exception {
    SecretKey key = KeyGenerator.getInstance("DES").generateKey();
    Encryption encrypter = new Encryption(key);
    String encrypted = encrypter.encrypt("Don't tell anybody!");
    String decrypted = encrypter.decrypt(encrypted);
    System.out.println(encrypted);
    System.out.println(decrypted);
  }
}
