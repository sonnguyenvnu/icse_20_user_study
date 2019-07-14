void applyLazyStateUpdatesForContainer(String componentKey,StateContainer container){
  StateHandler stateHandler;
synchronized (this) {
    if (mRoot == null) {
      return;
    }
    stateHandler=StateHandler.createShallowCopyForLazyStateUpdates(mStateHandler);
  }
  stateHandler.applyLazyStateUpdatesForContainer(componentKey,container);
}
