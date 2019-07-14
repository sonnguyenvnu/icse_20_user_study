final void doCalculateValue(long frameTimeNanos){
  final float value=calculateValue(frameTimeNanos);
  if (frameTimeNanos == mTimeNs) {
    throw new RuntimeException("Got a calculate value call multiple times in the same frame. This isn't expected.");
  }
  mTimeNs=frameTimeNanos;
  mValue=value;
}
