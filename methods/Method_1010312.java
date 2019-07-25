/** 
 * Execute an action with notification about action started/finished dispatching for top-most action.
 * @param r action to execute
 */
public void dispatch(Runnable r){
  final boolean traceEnabled=LOG.isTraceEnabled();
  final int actionLevel=myActionLevel.getAndIncrement();
  try {
    if (actionLevel == 0) {
      onActionStarted();
    }
    if (traceEnabled) {
      LOG.trace(String.format("Action started (level:%d)",actionLevel));
    }
    try {
      r.run();
    }
 catch (    RuntimeException ex) {
      logUnexpectedRuntimeException(ex);
      throw ex;
    }
  }
  finally {
    if (traceEnabled) {
      LOG.trace(String.format("Action finished (level:%d)",actionLevel));
    }
    if (myActionLevel.decrementAndGet() == 0) {
      onActionFinished();
    }
  }
}
