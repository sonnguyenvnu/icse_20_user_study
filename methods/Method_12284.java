synchronized void block() throws InterruptedException {
  while (!mCondition) {
    this.wait();
  }
}
