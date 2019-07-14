public long getCallDuration(){
  return SystemClock.elapsedRealtime() - callStartTime;
}
