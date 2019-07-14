void applyLazyStateUpdatesForContainer(String componentKey,StateContainer container){
  final List<StateUpdate> stateUpdatesForKey;
synchronized (this) {
    stateUpdatesForKey=mPendingLazyStateUpdates == null ? null : mPendingLazyStateUpdates.get(componentKey);
  }
  if (stateUpdatesForKey != null) {
    for (    StateUpdate update : stateUpdatesForKey) {
      container.applyStateUpdate(update);
    }
  }
}
