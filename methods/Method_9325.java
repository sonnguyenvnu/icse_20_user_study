public void needHideProgress(boolean cancel){
  if (progressRequestId != 0) {
    if (cancel) {
      ConnectionsManager.getInstance(currentAccount).cancelRequest(progressRequestId,true);
    }
    progressRequestId=0;
  }
  showEditDoneProgress(false);
}
