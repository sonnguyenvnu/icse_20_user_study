@Override public long alloc(int chunks,long prevPos,int prevChunks){
  long ret=s.allocReturnCode(chunks);
  if (prevPos >= 0)   s.free(prevPos,prevChunks);
  if (ret >= 0)   return ret;
  int alreadyAttemptedTier=s.tier;
  s.goToFirstTier();
  while (true) {
    if (s.tier != alreadyAttemptedTier) {
      ret=s.allocReturnCode(chunks);
      if (ret >= 0)       return ret;
    }
    s.nextTier();
  }
}
