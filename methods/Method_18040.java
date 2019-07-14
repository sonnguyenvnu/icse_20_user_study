@Override public float calculateValue(long frameTimeNanos){
  if (mLastValueTimeNs == Long.MIN_VALUE) {
    mStartTimeNs=frameTimeNanos;
    mLastValueTimeNs=frameTimeNanos;
    mExpectedEndTimeNs=mStartTimeNs + (mDurationMs * MS_IN_NANOS);
    return INITIAL_VALUE;
  }
  if (frameTimeNanos >= mExpectedEndTimeNs) {
    mLastValueTimeNs=frameTimeNanos;
    return END_VALUE;
  }
  mLastValueTimeNs=frameTimeNanos;
  return (float)(frameTimeNanos - mStartTimeNs) / (mExpectedEndTimeNs - mStartTimeNs);
}
