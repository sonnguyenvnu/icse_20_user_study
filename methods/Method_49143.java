@Override public boolean inUse(){
  return super.isLocked() || super.hasQueuedThreads();
}
