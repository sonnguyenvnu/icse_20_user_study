/** 
 * Records an execution and returns true if a retry has been scheduled for the  {@code failure}, else returns false and marks the execution and associated  {@code CompletableFuture} as complete.
 * @throws NullPointerException if {@code failure} is null
 * @throws IllegalStateException if a retry method has already been called or the execution is already complete
 */
public boolean retryOn(Throwable failure){
  Assert.notNull(failure,"failure");
  return retryFor(null,failure);
}
