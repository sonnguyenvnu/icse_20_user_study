/** 
 * Returns an exception to be used as the cause of the CancellationException thrown by {@link #get}. <p>Note: this method may be called speculatively.  There is no guarantee that the future will be cancelled if this method is called.
 */
@Nonnull private Throwable newCancellationCause(){
  return new CancellationException("Future.cancel() was called.");
}
