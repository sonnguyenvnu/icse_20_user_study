@Override public boolean isHealthCheckPassed(){
  for (  RuntimeHealthChecker runtimeHealthChecker : runtimeHealthCheckers) {
    if (!runtimeHealthChecker.isHealth()) {
      return false;
    }
  }
  return true;
}
