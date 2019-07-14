void recordResult(R result,Throwable failure){
  try {
    if (isFailure(result,failure))     state.get().recordFailure();
 else     state.get().recordSuccess();
  }
  finally {
    currentExecutions.decrementAndGet();
  }
}
