protected Message _decrypt(final Cipher cipher,Message msg) throws Exception {
  if (msg.getLength() == 0)   return msg;
  byte[] decrypted_msg;
  if (cipher == null)   decrypted_msg=code(msg.getRawBuffer(),msg.getOffset(),msg.getLength(),true);
 else   try {
    decrypted_msg=cipher.doFinal(msg.getRawBuffer(),msg.getOffset(),msg.getLength());
  }
 catch (  BadPaddingException|IllegalBlockSizeException e) {
    cipher.init(Cipher.DECRYPT_MODE,secret_key);
    throw e;
  }
  return msg.setBuffer(decrypted_msg);
}
