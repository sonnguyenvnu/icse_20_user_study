/** 
 * Copies the information from the given map of state updates into the map of pending state updates.
 */
private void copyStateUpdatesMap(@Nullable Map<String,List<StateUpdate>> pendingStateUpdates,@Nullable Map<String,List<StateUpdate>> pendingLazyStateUpdates,@Nullable Map<String,List<StateUpdate>> appliedStateUpdates){
  if ((pendingStateUpdates == null || pendingStateUpdates.isEmpty()) && (appliedStateUpdates == null || appliedStateUpdates.isEmpty())) {
    return;
  }
  maybeInitStateUpdatesMap();
synchronized (this) {
    if (pendingStateUpdates != null) {
      for (      String key : pendingStateUpdates.keySet()) {
        mPendingStateUpdates.put(key,createStateUpdatesList(pendingStateUpdates.get(key)));
      }
    }
    copyPendingLazyStateUpdates(pendingLazyStateUpdates);
    if (appliedStateUpdates != null) {
      for (      String key : appliedStateUpdates.keySet()) {
        mAppliedStateUpdates.put(key,createStateUpdatesList(appliedStateUpdates.get(key)));
      }
    }
  }
}
