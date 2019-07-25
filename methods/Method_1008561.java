@Override public void close(){
  if (isClosed.compareAndSet(false,true)) {
    decRef();
    logger.debug("store reference count on close: {}",refCounter.refCount());
  }
}
