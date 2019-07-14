/** 
 * This will be called by the framework when one of the  {@link Section} in the tree requests toupdate its own state. The generation of the ChangeSet will happen asynchronously in this SectionTree ChangesetThread.
 * @param key The unique key of the {@link Section} in the tree.
 * @param stateUpdate An implementation of {@link StateContainer.StateUpdate} that knows how totransition to the new state.
 */
synchronized void updateStateAsync(String key,StateContainer.StateUpdate stateUpdate,String attribution){
  if (mForceSyncStateUpdates) {
    updateState(key,stateUpdate,attribution);
  }
 else {
    mCalculateChangeSetRunnable.cancel();
    addStateUpdateInternal(key,stateUpdate,false);
    mCalculateChangeSetRunnable.ensurePosted(ApplyNewChangeSet.UPDATE_STATE_ASYNC,attribution);
  }
}
