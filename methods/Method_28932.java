private boolean poolInactive(){
  return this.internalPool == null || this.internalPool.isClosed();
}
