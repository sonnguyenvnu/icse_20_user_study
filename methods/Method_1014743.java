public void shutdown(final boolean interrupt){
  this.stopped=true;
  log.info("shutdown thread " + this.getServiceName() + " interrupt " + interrupt);
synchronized (this) {
    if (!this.hasNotified) {
      this.hasNotified=true;
      this.notify();
    }
  }
  try {
    if (interrupt) {
      this.thread.interrupt();
    }
    long beginTime=System.currentTimeMillis();
    this.thread.join(this.getJointime());
    long eclipseTime=System.currentTimeMillis() - beginTime;
    log.info("join thread " + this.getServiceName() + " eclipse time(ms) " + eclipseTime + " " + this.getJointime());
  }
 catch (  InterruptedException e) {
    log.error("Interrupted",e);
  }
}
