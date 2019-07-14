private void doHealthCheck(BizEvent event){
  SofaRuntimeManager sofaRuntimeManager=getSofaRuntimeManager(event.getBiz());
  if (!sofaRuntimeManager.isHealthCheckPassed()) {
    throw new RuntimeException("Health check failed.");
  }
}
