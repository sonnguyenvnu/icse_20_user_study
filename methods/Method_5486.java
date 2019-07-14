private long resolveTimeToLiveEdgeUs(long playbackPositionUs){
  if (!manifest.isLive) {
    return C.TIME_UNSET;
  }
  StreamElement currentElement=manifest.streamElements[streamElementIndex];
  int lastChunkIndex=currentElement.chunkCount - 1;
  long lastChunkEndTimeUs=currentElement.getStartTimeUs(lastChunkIndex) + currentElement.getChunkDurationUs(lastChunkIndex);
  return lastChunkEndTimeUs - playbackPositionUs;
}
