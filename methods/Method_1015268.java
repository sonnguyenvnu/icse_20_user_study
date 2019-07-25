private static int decode(byte[] array,int offset){
  byte b=array[offset];
  if (b >= 0) {
    return b;
  }
  int len=prefixLength(b);
  int codePoint=b & ((1 << (7 - len)) - 1);
  for (int i=offset + 1; i < offset + len; i++) {
    codePoint=(codePoint << 6) | (array[i] & 0b00111111);
  }
  return codePoint;
}
