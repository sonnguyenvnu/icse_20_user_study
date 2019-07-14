public void executionDone(ExecutionResult executionResult,HystrixCommandKey commandKey,HystrixThreadPoolKey threadPoolKey){
  HystrixCommandCompletion event=HystrixCommandCompletion.from(executionResult,commandKey,threadPoolKey);
  writeOnlyCommandCompletionSubject.onNext(event);
}
