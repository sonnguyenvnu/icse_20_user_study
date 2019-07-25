public synchronized TimeService start(){
  if (task == null || task.isDone())   task=timer.scheduleWithFixedDelay(this,interval,interval,TimeUnit.MILLISECONDS,false);
  return this;
}
