/** 
 * Handle whether the tiny red error indicator is shown near the error button at the bottom of the PDE
 */
public void updateErrorToggle(boolean hasErrors){
  if (errorTable != null) {
    footer.setNotification(errorTable.getParent(),hasErrors);
  }
}
