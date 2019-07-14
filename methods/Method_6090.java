private boolean isDriftTooLarge(long frameTimeNs,long releaseTimeNs){
  long elapsedFrameTimeNs=frameTimeNs - syncFramePresentationTimeNs;
  long elapsedReleaseTimeNs=releaseTimeNs - syncUnadjustedReleaseTimeNs;
  return Math.abs(elapsedReleaseTimeNs - elapsedFrameTimeNs) > MAX_ALLOWED_DRIFT_NS;
}
