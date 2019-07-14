@Override public void updateSelectedTrack(long playbackPositionUs,long bufferedDurationUs,long availableDurationUs,List<? extends MediaChunk> queue,MediaChunkIterator[] mediaChunkIterators){
  long nowMs=SystemClock.elapsedRealtime();
  int nonBlacklistedFormatCount=0;
  for (int i=0; i < length; i++) {
    if (!isBlacklisted(i,nowMs)) {
      nonBlacklistedFormatCount++;
    }
  }
  selectedIndex=random.nextInt(nonBlacklistedFormatCount);
  if (nonBlacklistedFormatCount != length) {
    nonBlacklistedFormatCount=0;
    for (int i=0; i < length; i++) {
      if (!isBlacklisted(i,nowMs) && selectedIndex == nonBlacklistedFormatCount++) {
        selectedIndex=i;
        return;
      }
    }
  }
}
