private void shutdown(AtomicBoolean complete){
  try {
    taskManager.stop(this.error);
    this.replicationConnectionPool.release();
    this.maxwellConnectionPool.release();
    this.rawMaxwellConnectionPool.release();
    complete.set(true);
  }
 catch (  Exception e) {
    LOGGER.error("Exception occurred during shutdown:",e);
  }
}
