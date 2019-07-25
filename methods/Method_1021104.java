@Override public void stream() throws InterruptedException {
  try (Closeable ignore=ShutdownHooks.addHook(this::onNodeShutdown)){
    streamInternal(new StartingState());
  }
 catch (  final IOException ex) {
    log.error("Failed to delete shutdown hook for subscription {}. This method should not throw any exception",getSubscription(),ex);
  }
}
