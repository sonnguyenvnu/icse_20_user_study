public static int positiveWithPrefixLength(final long value,final int prefixBitLen){
  assert value >= 0;
  assert prefixBitLen > 0 && prefixBitLen < 6;
  return numVariableBlocks(unsignedBitLength(value) + prefixBitLen);
}
