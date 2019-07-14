void markCommandDone(ExecutionResult executionResult,HystrixCommandKey commandKey,HystrixThreadPoolKey threadPoolKey,boolean executionStarted){
  HystrixThreadEventStream.getInstance().executionDone(executionResult,commandKey,threadPoolKey);
  if (executionStarted) {
    concurrentExecutionCount.decrementAndGet();
  }
}
