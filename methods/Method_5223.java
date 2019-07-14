private long getNowUnixTimeUs(){
  if (elapsedRealtimeOffsetMs != 0) {
    return C.msToUs(SystemClock.elapsedRealtime() + elapsedRealtimeOffsetMs);
  }
 else {
    return C.msToUs(System.currentTimeMillis());
  }
}
