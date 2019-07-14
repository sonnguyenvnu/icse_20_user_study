private static boolean isWithinLongRange(BigInteger v){
  return LONG_MIN.compareTo(v) <= 0 && v.compareTo(LONG_MAX) <= 0;
}
