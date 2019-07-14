@Override public void notifyChangeSetComplete(boolean isDataChanged,ChangeSetCompleteCallback changeSetCompleteCallback){
  if (mUseBackgroundChangeSets) {
    mRecyclerBinder.notifyChangeSetCompleteAsync(isDataChanged,changeSetCompleteCallback);
  }
 else {
    mRecyclerBinder.notifyChangeSetComplete(isDataChanged,changeSetCompleteCallback);
  }
}
