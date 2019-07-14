@Override protected void performLayout(boolean changed,int left,int top,int right,int bottom){
  if (mComponentTree != null) {
    if (mComponentTree.isReleased()) {
      throw new IllegalStateException("Trying to layout a LithoView holding onto a released ComponentTree");
    }
    if (mDoMeasureInLayout || mComponentTree.getMainThreadLayoutState() == null) {
      mComponentTree.measure(MeasureSpec.makeMeasureSpec(right - left,MeasureSpec.EXACTLY),MeasureSpec.makeMeasureSpec(bottom - top,MeasureSpec.EXACTLY),sLayoutSize,false);
      mHasNewComponentTree=false;
      mDoMeasureInLayout=false;
    }
    boolean wasMountTriggered=mComponentTree.layout();
    if (!wasMountTriggered && isIncrementalMountEnabled()) {
      performIncrementalMount();
    }
    if (!wasMountTriggered || shouldAlwaysLayoutChildren()) {
      performLayoutOnChildrenIfNecessary(this);
    }
  }
}
