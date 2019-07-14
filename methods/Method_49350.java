public static boolean isPowerOf2(long value){
  return value > 0 && Long.highestOneBit(value) == value;
}
