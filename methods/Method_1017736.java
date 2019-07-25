public static long choose(SourceOfRandomness random,long min,long max){
  checkRange(INTEGRAL,min,max);
  boolean noOverflowIssues=max < ((long)1 << 62) && min > -(((long)1) << 62);
  if (noOverflowIssues) {
    long range=(max - min) + 1;
    long mask=findNextPowerOfTwoLong(range) - 1;
    long generated;
    do {
      generated=Math.abs(random.nextLong()) & mask;
    }
 while (generated >= range);
    return generated + min;
  }
 else {
    return choose(random,BigInteger.valueOf(min),BigInteger.valueOf(max)).longValue();
  }
}
