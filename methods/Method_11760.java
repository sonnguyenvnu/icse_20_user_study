/** 
 * Asserts that  {@code runnable} throws an exception of type {@code expectedThrowable} whenexecuted. If it does, the exception object is returned. If it does not throw an exception, an {@link AssertionError} is thrown. If it throws the wrong type of exception, an {@code AssertionError} is thrown describing the mismatch; the exception that was actually thrown canbe obtained by calling  {@link AssertionError#getCause}.
 * @param expectedThrowable the expected type of the exception
 * @param runnable       a function that is expected to throw an exception when executed
 * @return the exception thrown by {@code runnable}
 * @since 4.13
 */
public static <T extends Throwable>T assertThrows(Class<T> expectedThrowable,ThrowingRunnable runnable){
  return assertThrows(null,expectedThrowable,runnable);
}
