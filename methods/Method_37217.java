public void setAutoScroll(int intervalInMillis,SparseIntArray intervalArray){
  if (0 == intervalInMillis) {
    return;
  }
  if (timer != null) {
    disableAutoScroll();
  }
  timer=new TimerHandler(this,intervalInMillis);
  timer.setSpecialInterval(intervalArray);
  startTimer();
}
