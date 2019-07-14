void performPendingControllerChanges(){
  for (int i=0; i < pendingControllerChanges.size(); i++) {
    ControllerChangeHandler.executeChange(pendingControllerChanges.get(i));
  }
  pendingControllerChanges.clear();
}
