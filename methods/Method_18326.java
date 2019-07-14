void mount(LayoutState layoutState,Rect currentVisibleArea,boolean processVisibilityOutputs){
  if (mTransientStateCount > 0 && mComponentTree != null && mComponentTree.isIncrementalMountEnabled()) {
    if (!mMountState.isDirty()) {
      return;
    }
 else {
      currentVisibleArea=new Rect(0,0,getWidth(),getHeight());
      processVisibilityOutputs=false;
    }
  }
  if (currentVisibleArea == null) {
    mPreviousMountVisibleRectBounds.setEmpty();
  }
 else {
    mPreviousMountVisibleRectBounds.set(currentVisibleArea);
  }
  mMountState.mount(layoutState,currentVisibleArea,processVisibilityOutputs);
}
