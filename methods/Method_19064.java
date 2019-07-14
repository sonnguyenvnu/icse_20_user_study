static StateUpdatesHolder acquireStateUpdatesHolder(){
  StateUpdatesHolder pendingStateUpdates=sStateUpdatesHolderPool.acquire();
  if (pendingStateUpdates == null) {
    pendingStateUpdates=new StateUpdatesHolder();
  }
  return pendingStateUpdates;
}
