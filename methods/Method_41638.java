protected void clearFromBusyWorkersList(WorkerThread wt){
synchronized (nextRunnableLock) {
    busyWorkers.remove(wt);
    nextRunnableLock.notifyAll();
  }
}
