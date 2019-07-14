private static int[] findCandidate(byte[] input,int ipIndex,int ipLimit,int inputOffset,int shift,short[] table,int skip){
  int candidateIndex=0;
  for (ipIndex+=1; ipIndex + bytesBetweenHashLookups(skip) <= ipLimit; ipIndex+=bytesBetweenHashLookups(skip++)) {
    int currentInt=SnappyInternalUtils.loadInt(input,ipIndex);
    int hash=hashBytes(currentInt,shift);
    candidateIndex=inputOffset + table[hash];
    assert candidateIndex >= 0;
    assert candidateIndex < ipIndex;
    table[hash]=(short)(ipIndex - inputOffset);
    if (currentInt == SnappyInternalUtils.loadInt(input,candidateIndex)) {
      break;
    }
  }
  return new int[]{ipIndex,candidateIndex,skip};
}
