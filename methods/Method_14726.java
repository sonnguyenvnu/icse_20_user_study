@Override public void start() throws Exception {
  if (executor.isTerminated() || executor.isTerminating() || executor.isShutdown()) {
    throw new IllegalStateException("Cannot restart");
  }
}
