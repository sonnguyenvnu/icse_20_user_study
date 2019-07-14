@Override public boolean shouldStartPlayback(long bufferedDurationUs,float playbackSpeed,boolean rebuffering){
  bufferedDurationUs=Util.getPlayoutDurationForMediaDuration(bufferedDurationUs,playbackSpeed);
  long minBufferDurationUs=rebuffering ? bufferForPlaybackAfterRebufferUs : bufferForPlaybackUs;
  return minBufferDurationUs <= 0 || bufferedDurationUs >= minBufferDurationUs || (!prioritizeTimeOverSizeThresholds && allocator.getTotalBytesAllocated() >= targetBufferSize);
}
