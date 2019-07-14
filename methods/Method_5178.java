@Override protected int getWindowIndexForChildWindowIndex(MediaSourceHolder mediaSourceHolder,int windowIndex){
  return windowIndex + mediaSourceHolder.firstWindowIndexInChild;
}
