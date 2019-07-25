public void await(long startOffset) throws InterruptedException {
  sync.acquireSharedInterruptibly(startOffset);
}
