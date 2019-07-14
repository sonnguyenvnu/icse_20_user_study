@Override public boolean isExecutedInThread(){
  return isolationStrategy == HystrixCommandProperties.ExecutionIsolationStrategy.THREAD;
}
