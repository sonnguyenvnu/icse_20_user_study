/** 
 * Collects all action runtime configurations.
 */
protected void collectActionRuntimes(){
  actions=actionsManager.getAllActionRuntimes();
  actions.sort(Comparator.comparing(ActionRuntime::getActionPath));
}
