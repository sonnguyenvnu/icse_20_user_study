public static void decryptBytesWithKeyFile(byte[] bytes,int offset,int length,File keyFile) throws Exception {
  byte[] key=new byte[32];
  byte[] iv=new byte[16];
  RandomAccessFile randomAccessFile=new RandomAccessFile(keyFile,"r");
  randomAccessFile.read(key,0,32);
  randomAccessFile.read(iv,0,16);
  randomAccessFile.close();
  Utilities.aesCtrDecryptionByteArray(bytes,key,iv,offset,length,0);
}
