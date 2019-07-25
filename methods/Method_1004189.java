@Stage("Segment") public boolean realloc(long fromPos,int oldChunks,int newChunks){
  if (fromPos + newChunks < hh.h().actualChunksPerSegmentTier && freeList.isRangeClear(fromPos + oldChunks,fromPos + newChunks)) {
    freeList.setRange(fromPos + oldChunks,fromPos + newChunks);
    return true;
  }
 else {
    return false;
  }
}
