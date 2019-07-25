public synchronized void store(T value) throws InterruptedException {
  while (storage != null) {
    this.wait();
  }
  storage=value;
  this.notify();
}
