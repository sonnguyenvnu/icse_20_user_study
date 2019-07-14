static boolean isEnteringRange(WorkingRange workingRange,int position,int firstVisibleIndex,int lastVisibleIndex,int firstFullyVisibleIndex,int lastFullyVisibleIndex){
  return workingRange.shouldEnterRange(position,firstVisibleIndex,lastVisibleIndex,firstFullyVisibleIndex,lastFullyVisibleIndex);
}
