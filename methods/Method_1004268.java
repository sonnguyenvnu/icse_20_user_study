@Override public long alloc(int chunks,long prevPos,int prevChunks){
  long ret=s.allocReturnCode(chunks);
  if (ret >= 0) {
    if (prevPos >= 0)     s.free(prevPos,prevChunks);
    return ret;
  }
  int firstAttemptedTier=s.tier;
  long firstAttemptedTierIndex=s.tierIndex;
  long firstAttemptedTierBaseAddr=s.tierBaseAddr;
  boolean cleanedFirstAttemptedTier=forcedOldDeletedEntriesCleanup(prevPos);
  s.goToFirstTier();
  while (true) {
    boolean visitingFirstAttemptedTier=s.tier == firstAttemptedTier;
    if (cleanedFirstAttemptedTier || !visitingFirstAttemptedTier) {
      ret=s.allocReturnCode(chunks);
      if (ret >= 0) {
        if (prevPos >= 0) {
          if (visitingFirstAttemptedTier) {
            s.free(prevPos,prevChunks);
          }
 else           if (s.tier < firstAttemptedTier) {
            int currentTier=s.tier;
            long currentTierIndex=s.tierIndex;
            long currentTierBaseAddr=s.tierBaseAddr;
            s.initSegmentTier(firstAttemptedTier,firstAttemptedTierIndex,firstAttemptedTierBaseAddr);
            s.free(prevPos,prevChunks);
            s.initSegmentTier(currentTier,currentTierIndex,currentTierBaseAddr);
          }
        }
        return ret;
      }
    }
    if (visitingFirstAttemptedTier && prevPos >= 0)     s.free(prevPos,prevChunks);
    s.nextTier();
  }
}
