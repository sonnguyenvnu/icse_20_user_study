private static long pack(final int bufferOffset,final int tokenIndex){
  return ((long)bufferOffset << 32) | tokenIndex;
}
