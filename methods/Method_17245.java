static long ceilingPowerOfTwo(long x){
  return 1L << -Long.numberOfLeadingZeros(x - 1);
}
