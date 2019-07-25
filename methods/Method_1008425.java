@Override public int await() throws InterruptedException, BrokenBarrierException {
  try {
    breakIfBroken();
    return super.await();
  }
 catch (  BrokenBarrierException bbe) {
    initCause(bbe);
    throw bbe;
  }
}
