private static byte[] getBigIntegerBytes(BigInteger value){
  byte[] bytes=value.toByteArray();
  if (bytes.length > 256) {
    byte[] correctedAuth=new byte[256];
    System.arraycopy(bytes,1,correctedAuth,0,256);
    return correctedAuth;
  }
  return bytes;
}
