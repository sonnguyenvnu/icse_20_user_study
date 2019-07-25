/** 
 * Sets the delegate  {@link StreamMessage} which will actually publish the stream.
 * @throws IllegalStateException if the delegate has been set already orif  {@link #close()} or {@link #close(Throwable)} was called already.
 */
protected void delegate(StreamMessage<T> delegate){
  requireNonNull(delegate,"delegate");
  if (!delegateUpdater.compareAndSet(this,null,delegate)) {
    throw new IllegalStateException("delegate set already");
  }
  if (abortPending != 0) {
    delegate.abort();
  }
  if (!completionFuture().isDone()) {
    delegate.completionFuture().handle((unused,cause) -> {
      if (cause == null) {
        completionFuture().complete(null);
      }
 else {
        completionFuture().completeExceptionally(cause);
      }
      return null;
    }
).exceptionally(CompletionActions::log);
  }
  safeOnSubscribeToDelegate();
}
