private static byte[] prepad(byte[] original){
  int offset=BinaryRange.BYTES - original.length;
  byte[] result=new byte[BinaryRange.BYTES];
  System.arraycopy(original,0,result,offset,original.length);
  return result;
}
