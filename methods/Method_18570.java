private void copyPendingLazyStateUpdates(@Nullable Map<String,List<StateUpdate>> pendingLazyStateUpdates){
  if (pendingLazyStateUpdates == null || pendingLazyStateUpdates.isEmpty()) {
    return;
  }
  maybeInitLazyStateUpdatesMap();
  for (  String key : pendingLazyStateUpdates.keySet()) {
    mPendingLazyStateUpdates.put(key,createStateUpdatesList(pendingLazyStateUpdates.get(key)));
  }
}
