@GuardedBy("this") private void invalidateLayoutData(){
  mEstimatedViewportCount=UNSET;
  mSizeForMeasure=null;
  for (int i=0, size=mComponentTreeHolders.size(); i < size; i++) {
    mComponentTreeHolders.get(i).invalidateTree();
  }
  if (Looper.myLooper() == Looper.getMainLooper()) {
    mInternalAdapter.notifyDataSetChanged();
  }
 else {
    mMainThreadHandler.removeCallbacks(mNotifyDatasetChangedRunnable);
    mMainThreadHandler.post(mNotifyDatasetChangedRunnable);
  }
}
