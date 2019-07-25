@Override public boolean cancel(boolean mayInterruptIfRunning){
  final boolean updated=!isDone() && completeExceptionally(new CancellationException());
  return updated || isCancelled();
}
