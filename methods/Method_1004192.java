/** 
 * Move only to next tiers, to avoid double visiting of relocated entries during iteration
 */
@Override public long alloc(int chunks,long prevPos,int prevChunks){
  long ret=s.allocReturnCode(chunks);
  if (prevPos >= 0)   s.free(prevPos,prevChunks);
  if (ret >= 0)   return ret;
  while (true) {
    s.nextTier();
    ret=s.allocReturnCode(chunks);
    if (ret >= 0)     return ret;
  }
}
