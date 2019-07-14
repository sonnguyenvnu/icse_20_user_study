private void clearStateUpdates(@Nullable Map<String,List<StateUpdate>> appliedStateUpdates){
synchronized (this) {
    if (appliedStateUpdates == null || mPendingStateUpdates == null || mPendingStateUpdates.isEmpty()) {
      return;
    }
  }
  for (  String key : appliedStateUpdates.keySet()) {
    final List<StateUpdate> pendingStateUpdatesForKey;
    final List<StateUpdate> pendingLazyStateUpdatesForKey;
synchronized (this) {
      pendingStateUpdatesForKey=mPendingStateUpdates.get(key);
      pendingLazyStateUpdatesForKey=mPendingLazyStateUpdates == null ? null : mPendingLazyStateUpdates.get(key);
    }
    if (pendingStateUpdatesForKey == null) {
      continue;
    }
    final List<StateUpdate> appliedStateUpdatesForKey=appliedStateUpdates.get(key);
    if (pendingStateUpdatesForKey.size() == appliedStateUpdatesForKey.size()) {
synchronized (this) {
        mPendingStateUpdates.remove(key);
        if (mPendingLazyStateUpdates != null) {
          mPendingLazyStateUpdates.remove(key);
        }
      }
    }
 else {
      pendingStateUpdatesForKey.removeAll(appliedStateUpdatesForKey);
      if (pendingLazyStateUpdatesForKey != null) {
        pendingLazyStateUpdatesForKey.removeAll(appliedStateUpdatesForKey);
      }
    }
  }
}
