private boolean shouldContinueFeeding(long drainStartTimeMs){
  return renderTimeLimitMs == C.TIME_UNSET || SystemClock.elapsedRealtime() - drainStartTimeMs < renderTimeLimitMs;
}
