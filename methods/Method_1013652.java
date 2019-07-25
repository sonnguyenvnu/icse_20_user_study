public boolean await(long startOffset,long miliSeconds) throws InterruptedException {
  return sync.tryAcquireSharedNanos(startOffset,miliSeconds * (1000 * 1000));
}
