/** 
 * Sets the result of this  {@code Future} to match the supplied input {@code Future} once thesupplied  {@code Future} is done, unless this {@code Future} has already been cancelled or set(including "set asynchronously," defined below). <p>If the supplied future is  {@linkplain #isDone done} when this method is called and the callis accepted, then this future is guaranteed to have been completed with the supplied future by the time this method returns. If the supplied future is not done and the call is accepted, then the future will be <i>set asynchronously</i>. Note that such a result, though not yet known, cannot by overridden by a call to a  {@code set*} method, only by a call to {@link #cancel}. <p>If the call  {@code setFuture(delegate)} is accepted and this {@code Future} is latercancelled, cancellation will be propagated to  {@code delegate}. Additionally, any call to {@code setFuture} after any cancellation will propagate cancellation to the supplied {@code Future}.
 * @param future the future to delegate to
 * @return true if the attempt was accepted, indicating that the {@code Future} was not previouslycancelled or set.
 * @since 19.0
 */
protected boolean setFuture(@Nonnull ListenableFuture<? extends V> future){
  Preconditions.checkNotNull(future);
  Object localValue=value;
  if (localValue == null) {
    if (future.isDone()) {
      return completeWithFuture(future,null);
    }
    SetFuture valueToSet=new SetFuture(future);
    if (ATOMIC_HELPER.casValue(this,null,valueToSet)) {
      try {
        future.addListener(valueToSet,DirectExecutor.INSTANCE);
      }
 catch (      Throwable t) {
        Failure failure;
        try {
          failure=new Failure(t);
        }
 catch (        Throwable oomMostLikely) {
          failure=Failure.FALLBACK_INSTANCE;
        }
        ATOMIC_HELPER.casValue(this,valueToSet,failure);
      }
      return true;
    }
    localValue=value;
  }
  if (localValue instanceof Cancellation) {
    future.cancel(((Cancellation)localValue).wasInterrupted);
  }
  return false;
}
