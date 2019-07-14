/** 
 * Asserts that  {@code runnable} throws an exception of type {@code expectedThrowable} whenexecuted. If it does, the exception object is returned. If it does not throw an exception, an {@link AssertionError} is thrown. If it throws the wrong type of exception, an {@code AssertionError} is thrown describing the mismatch; the exception that was actually thrown canbe obtained by calling  {@link AssertionError#getCause}.
 * @param message the identifying message for the {@link AssertionError} (<code>null</code>okay)
 * @param expectedThrowable the expected type of the exception
 * @param runnable a function that is expected to throw an exception when executed
 * @return the exception thrown by {@code runnable}
 * @since 4.13
 */
public static <T extends Throwable>T assertThrows(String message,Class<T> expectedThrowable,ThrowingRunnable runnable){
  try {
    runnable.run();
  }
 catch (  Throwable actualThrown) {
    if (expectedThrowable.isInstance(actualThrown)) {
      @SuppressWarnings("unchecked") T retVal=(T)actualThrown;
      return retVal;
    }
 else {
      String expected=formatClass(expectedThrowable);
      Class<? extends Throwable> actualThrowable=actualThrown.getClass();
      String actual=formatClass(actualThrowable);
      if (expected.equals(actual)) {
        expected+="@" + Integer.toHexString(System.identityHashCode(expectedThrowable));
        actual+="@" + Integer.toHexString(System.identityHashCode(actualThrowable));
      }
      String mismatchMessage=buildPrefix(message) + format("unexpected exception type thrown;",expected,actual);
      AssertionError assertionError=new AssertionError(mismatchMessage);
      assertionError.initCause(actualThrown);
      throw assertionError;
    }
  }
  String notThrownMessage=buildPrefix(message) + String.format("expected %s to be thrown, but nothing was thrown",formatClass(expectedThrowable));
  throw new AssertionError(notThrownMessage);
}
