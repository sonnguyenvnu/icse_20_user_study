/** 
 * This will be called by the framework when one of the  {@link Section} in the tree requests toupdate its own state. The generation of the ChangeSet will happen synchronously in the thread calling this method.
 * @param key The unique key of the {@link Section} in the tree.
 * @param stateUpdate An implementation of {@link StateContainer.StateUpdate} that knows how totransition to the new state.
 */
synchronized void updateState(String key,StateContainer.StateUpdate stateUpdate,String attribution){
  if (mAsyncStateUpdates) {
    updateStateAsync(key,stateUpdate,attribution);
  }
 else {
    mCalculateChangeSetOnMainThreadRunnable.cancel();
    addStateUpdateInternal(key,stateUpdate,false);
    mCalculateChangeSetOnMainThreadRunnable.ensurePosted(ApplyNewChangeSet.UPDATE_STATE,attribution);
  }
}
