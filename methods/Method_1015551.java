protected void suspend(long timeout){
  if (!suspended) {
    suspended=true;
    log.debug("suspending message garbage collection");
  }
  startResumeTask(timeout);
}
