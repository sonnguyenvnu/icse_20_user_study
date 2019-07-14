/** 
 * Adds a failure to the table if  {@code runnable} does not throw anexception of type  {@code expectedThrowable} when executed.Execution continues, but the test will fail at the end if the runnable does not throw an exception, or if it throws a different exception.
 * @param expectedThrowable the expected type of the exception
 * @param runnable       a function that is expected to throw an exception when executed
 * @since 4.13
 */
public void checkThrows(Class<? extends Throwable> expectedThrowable,ThrowingRunnable runnable){
  try {
    assertThrows(expectedThrowable,runnable);
  }
 catch (  AssertionError e) {
    addError(e);
  }
}
