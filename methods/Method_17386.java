public static int rank(long index,int bitNum){
  return Long.bitCount(index & ~(-1L << bitNum));
}
