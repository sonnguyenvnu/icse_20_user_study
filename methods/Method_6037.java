@Override public boolean isReady(){
  if (super.isReady() && (renderedFirstFrame || (dummySurface != null && surface == dummySurface) || getCodec() == null || tunneling)) {
    joiningDeadlineMs=C.TIME_UNSET;
    return true;
  }
 else   if (joiningDeadlineMs == C.TIME_UNSET) {
    return false;
  }
 else   if (SystemClock.elapsedRealtime() < joiningDeadlineMs) {
    return true;
  }
 else {
    joiningDeadlineMs=C.TIME_UNSET;
    return false;
  }
}
