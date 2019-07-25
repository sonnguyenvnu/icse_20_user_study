public void exit(boolean cleanup) throws IOException {
  DistributedFPSet.shutdown();
synchronized (this) {
    this.notify();
  }
}
