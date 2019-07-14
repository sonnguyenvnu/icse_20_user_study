public synchronized void shutdown() throws JanusGraphException {
  cleaner.close(CLEAN_SLEEP_TIME);
}
