public boolean isScheduleChanged(){
synchronized (sigLock) {
    return signaled;
  }
}
