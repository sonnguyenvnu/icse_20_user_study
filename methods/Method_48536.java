private static int unsignedBackwardLength(final long value){
  int bitLength=unsignedBitLength(value);
  assert bitLength > 0 && bitLength <= 64;
  return Math.max(3,1 + (bitLength <= 4 ? 0 : (1 + (bitLength - 5) / 7)));
}
