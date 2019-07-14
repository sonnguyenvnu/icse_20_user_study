/** 
 * Called when a future passed via setFuture has completed.
 * @param future the done future to complete this future with.
 * @param expected the expected value of the {@link #value} field.
 */
private boolean completeWithFuture(ListenableFuture<? extends V> future,Object expected){
  Object valueToSet;
  if (future instanceof TrustedFuture) {
    valueToSet=((AbstractFuture<?>)future).value;
  }
 else {
    try {
      V v=Uninterruptibles.getUninterruptibly(future);
      valueToSet=v == null ? NULL : v;
    }
 catch (    ExecutionException exception) {
      valueToSet=new Failure(exception.getCause());
    }
catch (    CancellationException cancellation) {
      valueToSet=new Cancellation(false,cancellation);
    }
catch (    Throwable t) {
      valueToSet=new Failure(t);
    }
  }
  if (ATOMIC_HELPER.casValue(AbstractFuture.this,expected,valueToSet)) {
    complete();
    return true;
  }
  return false;
}
