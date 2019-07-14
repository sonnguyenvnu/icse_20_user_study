synchronized void checkWorkingRangeAndDispatch(int position,int firstVisibleIndex,int lastVisibleIndex,int firstFullyVisibleIndex,int lastFullyVisibleIndex){
  if (mComponentTree != null) {
    mComponentTree.checkWorkingRangeAndDispatch(position,firstVisibleIndex,lastVisibleIndex,firstFullyVisibleIndex,lastFullyVisibleIndex);
  }
}
