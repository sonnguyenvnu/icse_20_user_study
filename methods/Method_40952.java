/** 
 * Registers the  {@code listener} to be called when an execution is successful for a {@link Policy}.
 */
public S onSuccess(CheckedConsumer<? extends ExecutionCompletedEvent<R>> listener){
  successListener=EventListener.of(Assert.notNull(listener,"listener"));
  return (S)this;
}
