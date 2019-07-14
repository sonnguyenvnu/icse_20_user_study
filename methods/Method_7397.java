private void handleStateChange(int state){
  if (state == STATE_ESTABLISHED && callStartTime == 0)   callStartTime=SystemClock.elapsedRealtime();
  if (listener != null) {
    listener.onConnectionStateChanged(state);
  }
}
