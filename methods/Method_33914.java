static byte[] urlEncode(byte[] bytes){
  byte[] b64Bytes=Base64Codec.encodeBytesToBytes(bytes,0,bytes.length,Base64Codec.URL_SAFE);
  int length=b64Bytes.length;
  while (b64Bytes[length - 1] == EQUALS) {
    length-=1;
  }
  byte[] result=new byte[length];
  System.arraycopy(b64Bytes,0,result,0,length);
  return result;
}
