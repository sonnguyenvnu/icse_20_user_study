@Override public long getDurationUs(long segmentNum,long periodDurationUs){
  return chunkIndex.durationsUs[(int)segmentNum];
}
