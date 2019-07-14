private long getLockTimeout(){
  if (this.getProperties().executionTimeoutEnabled().get()) {
    return this.getProperties().executionTimeoutInMilliseconds().get();
  }
  return DEFAULT_LOCK_TIMEOUT;
}
