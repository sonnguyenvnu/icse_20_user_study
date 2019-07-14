public long getSignaledNextFireTime(){
synchronized (sigLock) {
    return signaledNextFireTime;
  }
}
