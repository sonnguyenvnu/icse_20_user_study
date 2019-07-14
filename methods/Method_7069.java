private void handleStopRequest(String withError){
  delayedStopHandler.removeCallbacksAndMessages(null);
  delayedStopHandler.sendEmptyMessageDelayed(0,STOP_DELAY);
  updatePlaybackState(withError);
  stopSelf();
  serviceStarted=false;
  NotificationCenter.getInstance(currentAccount).removeObserver(this,NotificationCenter.messagePlayingPlayStateChanged);
  NotificationCenter.getInstance(currentAccount).removeObserver(this,NotificationCenter.messagePlayingDidStart);
  NotificationCenter.getInstance(currentAccount).removeObserver(this,NotificationCenter.messagePlayingDidReset);
}
