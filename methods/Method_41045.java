protected void notifySchedulerThread(long candidateNewNextFireTime){
  if (isSignalOnSchedulingChange()) {
    signaler.signalSchedulingChange(candidateNewNextFireTime);
  }
}
