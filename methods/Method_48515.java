private static long readUnsigned(ScanBuffer in){
  long value=0;
  byte b;
  do {
    b=in.getByte();
    value=value << 7 | (b & BIT_MASK);
  }
 while (b >= 0);
  return value;
}
