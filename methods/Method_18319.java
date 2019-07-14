@Override public void setHasTransientState(boolean hasTransientState){
  if (hasTransientState) {
    if (mTransientStateCount == 0 && mComponentTree != null && mComponentTree.isIncrementalMountEnabled()) {
      performIncrementalMount(new Rect(0,0,getWidth(),getHeight()),false);
    }
    mTransientStateCount++;
  }
 else {
    mTransientStateCount--;
    if (mTransientStateCount == 0 && mComponentTree != null && mComponentTree.isIncrementalMountEnabled()) {
      performIncrementalMount();
    }
    if (mTransientStateCount < 0) {
      mTransientStateCount=0;
    }
  }
  super.setHasTransientState(hasTransientState);
}
