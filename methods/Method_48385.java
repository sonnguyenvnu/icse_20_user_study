private static StaticBuffer nextBiggerBuffer(StaticBuffer buffer,boolean allowOverflow){
  int len=buffer.length();
  byte[] next=new byte[len];
  boolean carry=true;
  for (int i=len - 1; i >= 0; i--) {
    byte b=buffer.getByte(i);
    if (carry) {
      b++;
      if (b != 0)       carry=false;
    }
    next[i]=b;
  }
  if (carry && allowOverflow) {
    return zeroBuffer(len);
  }
 else   if (carry) {
    throw new IllegalArgumentException("Buffer overflow incrementing " + buffer);
  }
 else {
    return StaticArrayBuffer.of(next);
  }
}
