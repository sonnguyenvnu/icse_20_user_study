public void onFragmentDestroy(){
  ConnectionsManager.getInstance(currentAccount).cancelRequestsForGuid(classGuid);
  isFinished=true;
  if (actionBar != null) {
    actionBar.setEnabled(false);
  }
}
