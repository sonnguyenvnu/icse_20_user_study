@Override public int evaluateQueueSize(long playbackPositionUs,List<? extends MediaChunk> queue){
  long nowMs=clock.elapsedRealtime();
  if (!shouldEvaluateQueueSize(nowMs)) {
    return queue.size();
  }
  lastBufferEvaluationMs=nowMs;
  if (queue.isEmpty()) {
    return 0;
  }
  int queueSize=queue.size();
  MediaChunk lastChunk=queue.get(queueSize - 1);
  long playoutBufferedDurationBeforeLastChunkUs=Util.getPlayoutDurationForMediaDuration(lastChunk.startTimeUs - playbackPositionUs,playbackSpeed);
  long minDurationToRetainAfterDiscardUs=getMinDurationToRetainAfterDiscardUs();
  if (playoutBufferedDurationBeforeLastChunkUs < minDurationToRetainAfterDiscardUs) {
    return queueSize;
  }
  int idealSelectedIndex=determineIdealSelectedIndex(nowMs,formatBitrates);
  Format idealFormat=getFormat(idealSelectedIndex);
  for (int i=0; i < queueSize; i++) {
    MediaChunk chunk=queue.get(i);
    Format format=chunk.trackFormat;
    long mediaDurationBeforeThisChunkUs=chunk.startTimeUs - playbackPositionUs;
    long playoutDurationBeforeThisChunkUs=Util.getPlayoutDurationForMediaDuration(mediaDurationBeforeThisChunkUs,playbackSpeed);
    if (playoutDurationBeforeThisChunkUs >= minDurationToRetainAfterDiscardUs && format.bitrate < idealFormat.bitrate && format.height != Format.NO_VALUE && format.height < 720 && format.width != Format.NO_VALUE && format.width < 1280 && format.height < idealFormat.height) {
      return i;
    }
  }
  return queueSize;
}
