@Override public boolean allowsExecution(CircuitBreakerStats stats){
  return stats.getCurrentExecutions() < maxConcurrentExecutions();
}
