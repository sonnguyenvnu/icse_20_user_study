@Override protected long getMediaTimeForChildMediaTime(Void id,long mediaTimeMs){
  if (mediaTimeMs == C.TIME_UNSET) {
    return C.TIME_UNSET;
  }
  long startMs=C.usToMs(startUs);
  long clippedTimeMs=Math.max(0,mediaTimeMs - startMs);
  if (endUs != C.TIME_END_OF_SOURCE) {
    clippedTimeMs=Math.min(C.usToMs(endUs) - startMs,clippedTimeMs);
  }
  return clippedTimeMs;
}
