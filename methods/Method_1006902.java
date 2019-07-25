/** 
 * Clean up the context at the end of a step execution. Must be called once at the end of a step execution to honour the destruction callback contract from the  {@link StepScope}.
 */
public void close(){
  List<Exception> errors=new ArrayList<>();
  Map<String,Set<Runnable>> copy=Collections.unmodifiableMap(callbacks);
  for (  Entry<String,Set<Runnable>> entry : copy.entrySet()) {
    Set<Runnable> set=entry.getValue();
    for (    Runnable callback : set) {
      if (callback != null) {
        try {
          callback.run();
        }
 catch (        RuntimeException t) {
          errors.add(t);
        }
      }
    }
  }
  if (errors.isEmpty()) {
    return;
  }
  Exception error=errors.get(0);
  if (error instanceof RuntimeException) {
    throw (RuntimeException)error;
  }
 else {
    throw new UnexpectedJobExecutionException("Could not close step context, rethrowing first of " + errors.size() + " exceptions.",error);
  }
}
