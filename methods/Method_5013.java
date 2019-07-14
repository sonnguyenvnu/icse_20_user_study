/** 
 * Handles the given action.
 * @param action The action to be executed.
 */
public void handleAction(DownloadAction action){
  Assertions.checkState(!released);
  if (initialized) {
    addDownloadForAction(action);
    saveActions();
  }
 else {
    actionQueue.add(action);
  }
}
