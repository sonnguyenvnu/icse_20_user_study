private void setJoiningDeadlineMs(){
  joiningDeadlineMs=allowedJoiningTimeMs > 0 ? (SystemClock.elapsedRealtime() + allowedJoiningTimeMs) : C.TIME_UNSET;
}
