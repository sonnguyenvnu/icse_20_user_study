private EncryptionResult encryptData(byte[] data){
  byte[] file_secret=getRandomSecret();
  int extraLen=32 + Utilities.random.nextInt(256 - 32 - 16);
  while ((data.length + extraLen) % 16 != 0) {
    extraLen++;
  }
  byte[] padding=new byte[extraLen];
  Utilities.random.nextBytes(padding);
  padding[0]=(byte)extraLen;
  byte[] paddedData=new byte[extraLen + data.length];
  System.arraycopy(padding,0,paddedData,0,extraLen);
  System.arraycopy(data,0,paddedData,extraLen,data.length);
  byte[] file_hash=Utilities.computeSHA256(paddedData);
  byte[] file_secret_hash=Utilities.computeSHA512(file_secret,file_hash);
  byte[] file_key=new byte[32];
  System.arraycopy(file_secret_hash,0,file_key,0,32);
  byte[] file_iv=new byte[16];
  System.arraycopy(file_secret_hash,32,file_iv,0,16);
  Utilities.aesCbcEncryptionByteArraySafe(paddedData,file_key,file_iv,0,paddedData.length,0,1);
  byte[] key=new byte[32];
  System.arraycopy(saltedPassword,0,key,0,32);
  byte[] iv=new byte[16];
  System.arraycopy(saltedPassword,32,iv,0,16);
  byte[] decryptedSecret=new byte[32];
  System.arraycopy(secureSecret,0,decryptedSecret,0,32);
  Utilities.aesCbcEncryptionByteArraySafe(decryptedSecret,key,iv,0,decryptedSecret.length,0,0);
  byte[] secret_hash=Utilities.computeSHA512(decryptedSecret,file_hash);
  byte[] file_secret_key=new byte[32];
  System.arraycopy(secret_hash,0,file_secret_key,0,32);
  byte[] file_secret_iv=new byte[16];
  System.arraycopy(secret_hash,32,file_secret_iv,0,16);
  byte[] encrypyed_file_secret=new byte[32];
  System.arraycopy(file_secret,0,encrypyed_file_secret,0,32);
  Utilities.aesCbcEncryptionByteArraySafe(encrypyed_file_secret,file_secret_key,file_secret_iv,0,encrypyed_file_secret.length,0,1);
  return new EncryptionResult(paddedData,encrypyed_file_secret,file_secret,file_hash,file_key,file_iv);
}
