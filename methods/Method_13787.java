public static int getInt2(byte[] b,int offset){
  return ((b[offset++] & 0x000000FF) | (b[offset] & 0x0000FF00));
}
