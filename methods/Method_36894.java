public void stopDetectPage(){
  if (isDetectingPageAppear) {
    TimerSupport timerSupport=mTangramEngine.getService(TimerSupport.class);
    timerSupport.unregister(mOnTickListener);
    isDetectingPageAppear=false;
  }
}
