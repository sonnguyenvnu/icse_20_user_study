protected void checkRunning(){
  if (!stat.compareAndSet(STAT_RUNNING,STAT_RUNNING)) {
    throw new IllegalStateException("Already closed!");
  }
}
