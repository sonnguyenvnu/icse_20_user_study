@Override public long getAdjustedSeekPositionUs(long positionUs,SeekParameters seekParameters){
  StreamElement streamElement=manifest.streamElements[streamElementIndex];
  int chunkIndex=streamElement.getChunkIndex(positionUs);
  long firstSyncUs=streamElement.getStartTimeUs(chunkIndex);
  long secondSyncUs=firstSyncUs < positionUs && chunkIndex < streamElement.chunkCount - 1 ? streamElement.getStartTimeUs(chunkIndex + 1) : firstSyncUs;
  return Util.resolveSeekPositionUs(positionUs,seekParameters,firstSyncUs,secondSyncUs);
}
