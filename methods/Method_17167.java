/** 
 * Decorates the remapping function to record statistics if enabled. 
 */
default <T,U,R>BiFunction<? super T,? super U,? extends R> statsAware(BiFunction<? super T,? super U,? extends R> remappingFunction,boolean recordMiss,boolean recordLoad,boolean recordLoadFailure){
  if (!isRecordingStats()) {
    return remappingFunction;
  }
  return (t,u) -> {
    R result;
    if ((u == null) && recordMiss) {
      statsCounter().recordMisses(1);
    }
    long startTime=statsTicker().read();
    try {
      result=remappingFunction.apply(t,u);
    }
 catch (    RuntimeException|Error e) {
      if (recordLoadFailure) {
        statsCounter().recordLoadFailure(statsTicker().read() - startTime);
      }
      throw e;
    }
    long loadTime=statsTicker().read() - startTime;
    if (recordLoad) {
      if (result == null) {
        statsCounter().recordLoadFailure(loadTime);
      }
 else {
        statsCounter().recordLoadSuccess(loadTime);
      }
    }
    return result;
  }
;
}
