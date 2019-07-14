void attach(){
  assertMainThread();
  if (mLithoView == null) {
    throw new IllegalStateException("Trying to attach a ComponentTree without a set View");
  }
  if (mIncrementalMountHelper != null) {
    mIncrementalMountHelper.onAttach(mLithoView);
  }
  final int componentRootId;
synchronized (this) {
    mIsAttached=true;
    setBestMainThreadLayoutAndReturnOldLayout();
    if (mRoot == null) {
      throw new IllegalStateException("Trying to attach a ComponentTree with a null root. Is released: " + mReleased + ", Released Component name is: " + mReleasedComponent);
    }
    componentRootId=mRoot.getId();
  }
  final int viewWidth=mLithoView.getMeasuredWidth();
  final int viewHeight=mLithoView.getMeasuredHeight();
  if (viewWidth == 0 && viewHeight == 0) {
    return;
  }
  final boolean needsAndroidLayout=!isCompatibleComponentAndSize(mMainThreadLayoutState,componentRootId,viewWidth,viewHeight);
  if (needsAndroidLayout || mLithoView.isMountStateDirty()) {
    mLithoView.requestLayout();
  }
 else {
    mLithoView.rebind();
  }
}
