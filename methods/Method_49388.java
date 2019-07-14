/** 
 * VAVR Future.await will throw InterruptedException wrapped in a FatalException. If the Thread was in Object.wait, the interrupted flag will be cleared as a side effect and needs to be reset. This method checks that the underlying cause of the FatalException is InterruptedException and resets the interrupted flag.
 * @param result the future to wait on
 * @throws PermanentBackendException if the thread was interrupted while waiting for the future result
 */
private void interruptibleWait(final Future<?> result) throws PermanentBackendException {
  try {
    result.await();
  }
 catch (  Exception e) {
    if (e instanceof InterruptedException) {
      Thread.currentThread().interrupt();
    }
    throw new PermanentBackendException(e);
  }
}
