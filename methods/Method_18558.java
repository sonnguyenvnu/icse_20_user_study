synchronized boolean hasPendingUpdates(){
  if (mPendingStateUpdates != null && !mPendingStateUpdates.isEmpty()) {
    for (    List<StateUpdate> entry : mPendingStateUpdates.values()) {
      if (!entry.isEmpty()) {
        return true;
      }
    }
  }
  return false;
}
