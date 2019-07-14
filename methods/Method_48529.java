public static long[] readPositiveWithPrefix(final ReadBuffer in,final int prefixBitLen){
  assert prefixBitLen > 0 && prefixBitLen < 6;
  int first=unsignedByte(in.getByte());
  int deltaLen=8 - prefixBitLen;
  long prefix=first >> deltaLen;
  long value=first & ((1 << (deltaLen - 1)) - 1);
  if (((first >>> (deltaLen - 1)) & 1) == 1) {
    int deltaPos=in.getPosition();
    long remainder=readUnsigned(in);
    deltaPos=in.getPosition() - deltaPos;
    assert deltaPos > 0;
    value=(value << (deltaPos * 7)) + remainder;
  }
  return new long[]{value,prefix};
}
