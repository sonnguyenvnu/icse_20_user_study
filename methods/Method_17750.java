@Override public boolean shouldExitRange(int position,int firstVisibleIndex,int lastVisibleIndex,int firstFullyVisibleIndex,int lastFullyVisibleIndex){
  return !isInRange(position,firstVisibleIndex,lastVisibleIndex,mOffset);
}
