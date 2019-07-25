@Override public synchronized void await() throws InterruptedException {
  while (!isTerminated) {
    this.wait();
  }
}
