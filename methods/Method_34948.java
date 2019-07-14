public static long byteArray2Long(byte[] b){
  int value=0;
  for (int i=0; i < 8; i++) {
    int shift=(8 - 1 - i) * 8;
    value+=(b[i] & 0x000000FF) << shift;
  }
  return value;
}
