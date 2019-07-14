/** 
 * Removes a list of state updates that have been applied from the pending state updates list and updates the map of current components with the given components.
 * @param stateHandler state handler that was used to apply state updates in a layout pass
 * @param isNestedTreeResolutionExperimentEnabled is the NestedTree resolution experiment enabled
 */
void commit(StateHandler stateHandler,boolean isNestedTreeResolutionExperimentEnabled){
  clearStateUpdates(isNestedTreeResolutionExperimentEnabled ? stateHandler.getAppliedStateUpdates() : stateHandler.getPendingStateUpdates());
  clearUnusedStateContainers(this,stateHandler);
  copyCurrentStateContainers(stateHandler.getStateContainers());
  copyPendingStateTransitions(stateHandler.getPendingStateUpdateTransitions());
}
