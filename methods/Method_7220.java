public static byte[] getBigIntegerBytes(BigInteger value){
  byte[] bytes=value.toByteArray();
  if (bytes.length > 256) {
    byte[] correctedAuth=new byte[256];
    System.arraycopy(bytes,1,correctedAuth,0,256);
    return correctedAuth;
  }
 else   if (bytes.length < 256) {
    byte[] correctedAuth=new byte[256];
    System.arraycopy(bytes,0,correctedAuth,256 - bytes.length,bytes.length);
    for (int a=0; a < 256 - bytes.length; a++) {
      correctedAuth[a]=0;
    }
    return correctedAuth;
  }
  return bytes;
}
