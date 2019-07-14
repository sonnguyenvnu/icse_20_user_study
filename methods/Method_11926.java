/** 
 * Adds to the table the exception, if any, thrown from  {@code callable}. Execution continues, but the test will fail at the end if {@code callable} threw an exception.
 */
public <T>T checkSucceeds(Callable<T> callable){
  try {
    return callable.call();
  }
 catch (  AssumptionViolatedException e) {
    AssertionError error=new AssertionError("Callable threw AssumptionViolatedException");
    error.initCause(e);
    addError(error);
    return null;
  }
catch (  Throwable e) {
    addError(e);
    return null;
  }
}
