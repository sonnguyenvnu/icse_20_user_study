public Set<HashedWheelTimeout> stop(){
  if (Thread.currentThread() == workerThread) {
    throw new IllegalStateException(HashedWheelTimer.class.getSimpleName() + ".stop() cannot be called from ");
  }
  if (!WORKER_STATE_UPDATER.compareAndSet(this,WORKER_STATE_STARTED,WORKER_STATE_SHUTDOWN)) {
    WORKER_STATE_UPDATER.set(this,WORKER_STATE_SHUTDOWN);
    return Collections.emptySet();
  }
  boolean interrupted=false;
  while (workerThread.isAlive()) {
    workerThread.interrupt();
    try {
      workerThread.join(100);
    }
 catch (    InterruptedException ignored) {
      interrupted=true;
    }
  }
  if (interrupted) {
    Thread.currentThread().interrupt();
  }
  return worker.unprocessedTimeouts();
}
