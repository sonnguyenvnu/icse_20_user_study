@Override public void onPrepared(MediaPeriod source){
  handler.obtainMessage(MSG_PERIOD_PREPARED,source).sendToTarget();
}
