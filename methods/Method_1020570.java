public void run(int refreshIntervalTime){
  if (refreshTimer != null && !isTimerRunning) {
    this.timerTask=new TimerTask(){
      @Override public void run(){
        timerRunnable.run();
      }
    }
;
    isTimerRunning=true;
    refreshTimer.schedule(timerTask,refreshIntervalTime,refreshIntervalTime);
  }
}
