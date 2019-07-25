public void init(Runnable timerRunnable,int refreshIntervalTime){
  this.timerRunnable=timerRunnable;
  if (refreshIntervalTime > 0 && refreshTimer == null) {
    refreshTimer=new Timer();
  }
}
