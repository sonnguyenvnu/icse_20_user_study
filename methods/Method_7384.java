@Override public void onConnectionStateChanged(int newState){
  if (newState == STATE_FAILED) {
    callFailed();
    return;
  }
  if (newState == STATE_ESTABLISHED) {
    if (connectingSoundRunnable != null) {
      AndroidUtilities.cancelRunOnUIThread(connectingSoundRunnable);
      connectingSoundRunnable=null;
    }
    if (spPlayID != 0) {
      soundPool.stop(spPlayID);
      spPlayID=0;
    }
    if (!wasEstablished) {
      wasEstablished=true;
      if (!isProximityNear) {
        Vibrator vibrator=(Vibrator)getSystemService(VIBRATOR_SERVICE);
        if (vibrator.hasVibrator())         vibrator.vibrate(100);
      }
      AndroidUtilities.runOnUIThread(new Runnable(){
        @Override public void run(){
          if (controller == null)           return;
          int netType=getStatsNetworkType();
          StatsController.getInstance(currentAccount).incrementTotalCallsTime(netType,5);
          AndroidUtilities.runOnUIThread(this,5000);
        }
      }
,5000);
      if (isOutgoing)       StatsController.getInstance(currentAccount).incrementSentItemsCount(getStatsNetworkType(),StatsController.TYPE_CALLS,1);
 else       StatsController.getInstance(currentAccount).incrementReceivedItemsCount(getStatsNetworkType(),StatsController.TYPE_CALLS,1);
    }
  }
  if (newState == STATE_RECONNECTING) {
    if (spPlayID != 0)     soundPool.stop(spPlayID);
    spPlayID=soundPool.play(spConnectingId,1,1,0,-1,1);
  }
  dispatchStateChanged(newState);
}
