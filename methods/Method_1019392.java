private String encode(byte[] bytes){
  StringBuilder buf=new StringBuilder();
  for (int i=0; i < bytes.length; i+=3) {
    int b1=(bytes[i]) & 0xFF;
    int b2=(i + 1 < bytes.length ? bytes[i + 1] : (byte)0) & 0xFF;
    int b3=(i + 2 < bytes.length ? bytes[i + 2] : (byte)0) & 0xFF;
    append3bytes(buf,b1,b2,b3);
  }
  return buf.toString();
}
