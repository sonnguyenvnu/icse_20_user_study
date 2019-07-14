public void completeStream(){
  if (atomicInteger.decrementAndGet() <= 0) {
    countDownLatch.countDown();
  }
}
