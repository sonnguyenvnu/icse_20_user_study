private void showUsersResult(ArrayList<TLRPC.User> newResult,SparseArray<TLRPC.User> newMap,boolean notify){
  searchResultUsernames=newResult;
  searchResultUsernamesMap=newMap;
  if (cancelDelayRunnable != null) {
    AndroidUtilities.cancelRunOnUIThread(cancelDelayRunnable);
    cancelDelayRunnable=null;
  }
  if (notify) {
    notifyDataSetChanged();
    delegate.needChangePanelVisibility(!searchResultUsernames.isEmpty());
  }
}
