/** 
 * Registers the  {@code listener} to be called when an execution fails for a {@link Policy}.
 */
public S onFailure(CheckedConsumer<? extends ExecutionCompletedEvent<R>> listener){
  failureListener=EventListener.of(Assert.notNull(listener,"listener"));
  return (S)this;
}
