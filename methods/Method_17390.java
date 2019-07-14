@SuppressWarnings("UnnecessaryParentheses") private static long shrinkOffset(long isLastIndex,int offset){
  long conMask=((1L << offset) - 1);
  return (isLastIndex & conMask) | (((~conMask) & isLastIndex) >>> 1);
}
