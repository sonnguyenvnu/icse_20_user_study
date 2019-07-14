void incrementalMountComponent(){
  assertMainThread();
  if (!mIncrementalMountEnabled) {
    throw new IllegalStateException("Calling incrementalMountComponent() but incremental mount" + " is not enabled");
  }
  if (mLithoView == null) {
    return;
  }
  final Rect currentVisibleArea=new Rect();
  if (ComponentsConfiguration.incrementalMountWhenNotVisible) {
    boolean isVisible=mIsAttached && mLithoView.getLocalVisibleRect(currentVisibleArea);
    if (!isVisible) {
      currentVisibleArea.setEmpty();
    }
    mountComponent(currentVisibleArea,true);
  }
 else {
    if (mLithoView.getLocalVisibleRect(currentVisibleArea) || animatingRootBoundsFromZero(currentVisibleArea)) {
      mountComponent(currentVisibleArea,true);
    }
  }
}
