@Override public long getTotalBufferedDuration(){
  return Math.max(0,C.usToMs(playbackInfo.totalBufferedDurationUs));
}
