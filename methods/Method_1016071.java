@Override public synchronized void close(){
  if (state.compareAndSet(State.STARTING,State.CLOSED) || state.compareAndSet(State.STARTED,State.CLOSED)) {
    try {
      if (newLifecycleManager != null) {
        newLifecycleManager.notifyShutdown();
      }
      preDestroyMonitor.close();
    }
 catch (    Exception e) {
      log.error("While stopping instances",e);
    }
 finally {
      objectStates.clear();
      preDestroyActionCache.clear();
    }
  }
}
