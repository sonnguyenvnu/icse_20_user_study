@Override public Optional<Listener> acquire(ContextT context){
  final Partition partition=resolvePartition(context);
  try {
    lock.lock();
    if (getInflight() >= getLimit() && partition.isLimitExceeded()) {
      lock.unlock();
      if (partition.backoffMillis > 0 && delayedThreads.get() < maxDelayedThreads) {
        try {
          delayedThreads.incrementAndGet();
          TimeUnit.MILLISECONDS.sleep(partition.backoffMillis);
        }
 catch (        InterruptedException e) {
          Thread.currentThread().interrupt();
        }
 finally {
          delayedThreads.decrementAndGet();
        }
      }
      return createRejectedListener();
    }
    partition.acquire();
    final Listener listener=createListener();
    return Optional.of(new Listener(){
      @Override public void onSuccess(){
        listener.onSuccess();
        releasePartition(partition);
      }
      @Override public void onIgnore(){
        listener.onIgnore();
        releasePartition(partition);
      }
      @Override public void onDropped(){
        listener.onDropped();
        releasePartition(partition);
      }
    }
);
  }
  finally {
    if (lock.isHeldByCurrentThread())     lock.unlock();
  }
}
