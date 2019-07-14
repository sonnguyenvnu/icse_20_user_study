static boolean isExitingRange(WorkingRange workingRange,int position,int firstVisibleIndex,int lastVisibleIndex,int firstFullyVisibleIndex,int lastFullyVisibleIndex){
  return workingRange.shouldExitRange(position,firstVisibleIndex,lastVisibleIndex,firstFullyVisibleIndex,lastFullyVisibleIndex);
}
