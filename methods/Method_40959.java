/** 
 * Registers the  {@code listener} to be called before an execution is retried.<p>Note: Any exceptions that are thrown from within the  {@code listener} are ignored.</p>
 */
public RetryPolicy<R> onRetry(CheckedConsumer<? extends ExecutionAttemptedEvent<R>> listener){
  retryListener=EventListener.ofAttempt(Assert.notNull(listener,"listener"));
  return this;
}
