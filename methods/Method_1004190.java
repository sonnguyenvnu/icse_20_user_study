@Stage("Segment") public void free(long fromPos,int chunks){
  tierEntries(tierEntries() - 1);
  freeList.clearRange(fromPos,fromPos + chunks);
  if (fromPos < lowestPossiblyFreeChunk())   lowestPossiblyFreeChunk(fromPos);
}
