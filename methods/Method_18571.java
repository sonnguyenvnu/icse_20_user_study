/** 
 * Copies the list of given state containers into the map that holds the current state containers of components.
 */
private void copyCurrentStateContainers(Map<String,StateContainer> stateContainers){
  if (stateContainers == null || stateContainers.isEmpty()) {
    return;
  }
synchronized (this) {
    maybeInitStateContainers();
    mStateContainers.clear();
    mStateContainers.putAll(stateContainers);
  }
}
