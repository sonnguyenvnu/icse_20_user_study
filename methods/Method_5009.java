/** 
 * Sets the requirements needed to be met to start downloads.
 * @param requirements Need to be met to start downloads.
 */
public void setRequirements(Requirements requirements){
  Assertions.checkState(!released);
  if (requirements.equals(requirementsWatcher.getRequirements())) {
    return;
  }
  requirementsWatcher.stop();
  notifyListenersRequirementsStateChange(watchRequirements(requirements));
}
