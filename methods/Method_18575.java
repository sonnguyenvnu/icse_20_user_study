private synchronized void maybeInitStateUpdatesMap(){
  if (mPendingStateUpdates == null) {
    mPendingStateUpdates=new HashMap<>(INITIAL_MAP_CAPACITY);
  }
  if (mAppliedStateUpdates == null) {
    mAppliedStateUpdates=new HashMap<>(INITIAL_MAP_CAPACITY);
  }
}
