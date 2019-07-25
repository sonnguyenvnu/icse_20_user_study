public boolean put(T msg,int timeoutMillis) throws Pausable {
  final Task t=Task.getCurrentTask();
  long begin=System.currentTimeMillis();
  long time=timeoutMillis;
  while (!put(msg,t)) {
    t.timer.setTimer(time);
    t.scheduler.scheduleTimer(t.timer);
    Task.pause(this);
    t.timer.cancel();
    removeSpaceAvailableListener(t);
    time=timeoutMillis - (System.currentTimeMillis() - begin);
    if (time <= 0) {
      return false;
    }
  }
  return true;
}
