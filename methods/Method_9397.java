private byte[] decryptSecret(byte[] secret,byte[] passwordHash){
  if (secret == null || secret.length != 32) {
    return null;
  }
  byte[] key=new byte[32];
  System.arraycopy(passwordHash,0,key,0,32);
  byte[] iv=new byte[16];
  System.arraycopy(passwordHash,32,iv,0,16);
  byte[] decryptedSecret=new byte[32];
  System.arraycopy(secret,0,decryptedSecret,0,32);
  Utilities.aesCbcEncryptionByteArraySafe(decryptedSecret,key,iv,0,decryptedSecret.length,0,0);
  return decryptedSecret;
}
