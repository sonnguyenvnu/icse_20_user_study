public byte[] recombine(byte[][] encoded,int startOffset,int truncateLength){
  byte[] withoutPrefix=Erasure.recombine(encoded,truncateLength,nOriginalFragments,nAllowedFailures);
  byte[] withPrefix=new byte[startOffset + withoutPrefix.length];
  System.arraycopy(withoutPrefix,0,withPrefix,startOffset,withoutPrefix.length);
  return withPrefix;
}
