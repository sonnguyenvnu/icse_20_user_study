/** 
 * Stop timer task and kill all running Spark contexts.
 */
@OnShutdown public void shutdown(){
  contextTimeoutTimer.cancel();
  for (  SparkContextState sparkContextState : sparkContextsStates.values()) {
    deleteContext(sparkContextState.contextName);
  }
}
