static public void initialize(RefineServlet servlet){
  ImportingManager.servlet=servlet;
  service=Executors.newSingleThreadScheduledExecutor();
  service.scheduleWithFixedDelay(new CleaningTimerTask(),TIMER_PERIOD,TIMER_PERIOD,TimeUnit.MINUTES);
}
