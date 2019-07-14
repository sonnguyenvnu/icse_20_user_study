void checkWorkingRangeAndDispatch(int position,int firstVisibleIndex,int lastVisibleIndex,int firstFullyVisibleIndex,int lastFullyVisibleIndex,WorkingRangeStatusHandler stateHandler){
  if (mWorkingRangeContainer == null) {
    return;
  }
  mWorkingRangeContainer.checkWorkingRangeAndDispatch(position,firstVisibleIndex,lastVisibleIndex,firstFullyVisibleIndex,lastFullyVisibleIndex,stateHandler);
}
