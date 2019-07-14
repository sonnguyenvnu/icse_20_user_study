@SuppressWarnings("UnnecessaryParentheses") private static long extendZero(final long isLastIndex,final int offset){
  long constantPartMask=(1L << offset) - 1;
  return (isLastIndex & constantPartMask) | ((isLastIndex << 1L) & (~(constantPartMask)) & (~(1L << offset)));
}
