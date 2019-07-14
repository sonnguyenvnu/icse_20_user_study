@Override public long getPositionUs(){
  long positionUs=baseUs;
  if (started) {
    long elapsedSinceBaseMs=clock.elapsedRealtime() - baseElapsedMs;
    if (playbackParameters.speed == 1f) {
      positionUs+=C.msToUs(elapsedSinceBaseMs);
    }
 else {
      positionUs+=playbackParameters.getMediaTimeUsForPlayoutTimeMs(elapsedSinceBaseMs);
    }
  }
  return positionUs;
}
