public void start(long period,TimeUnit unit){
  if (myTimerTask != null) {
    throw new IllegalStateException();
  }
  myTimerTask=myScheduler.scheduleWithFixedDelay(new Runnable(){
    public void run(){
      scheduleProcessing();
    }
  }
,period,period,unit);
}
