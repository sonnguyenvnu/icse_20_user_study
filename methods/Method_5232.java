@Override public long getSegmentNum(long timeUs,long periodDurationUs){
  return chunkIndex.getChunkIndex(timeUs + timeOffsetUs);
}
