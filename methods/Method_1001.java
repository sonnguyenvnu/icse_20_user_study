private boolean isInactive(){
  return mMonotonicClock.now() - mLastDrawnTimeMs > mInactivityThresholdMs;
}
