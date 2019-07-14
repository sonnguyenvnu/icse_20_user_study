/** 
 * Wait for the test task, returning the exception thrown by the test if the test failed, an exception indicating a timeout if the test timed out, or {@code null} if the test passed.
 */
private Throwable getResult(FutureTask<Throwable> task,Thread thread){
  try {
    if (timeout > 0) {
      return task.get(timeout,timeUnit);
    }
 else {
      return task.get();
    }
  }
 catch (  InterruptedException e) {
    return e;
  }
catch (  ExecutionException e) {
    return e.getCause();
  }
catch (  TimeoutException e) {
    return createTimeoutException(thread);
  }
}
