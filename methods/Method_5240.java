private long getNowUnixTimeUs(){
  if (elapsedRealtimeOffsetMs != 0) {
    return (SystemClock.elapsedRealtime() + elapsedRealtimeOffsetMs) * 1000;
  }
 else {
    return System.currentTimeMillis() * 1000;
  }
}
