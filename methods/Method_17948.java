/** 
 * Picks the best LayoutState and sets it in mMainThreadLayoutState. The return value is a LayoutState that must be released (after the lock is released). This awkward contract is necessary to ensure thread-safety.
 */
@CheckReturnValue @ReturnsOwnership @ThreadConfined(ThreadConfined.UI) @GuardedBy("this") private LayoutState setBestMainThreadLayoutAndReturnOldLayout(){
  assertHoldsLock(this);
  final boolean isMainThreadLayoutBest=isBestMainThreadLayout();
  if (isMainThreadLayoutBest) {
    final LayoutState toRelease=mBackgroundLayoutState;
    mBackgroundLayoutState=null;
    return toRelease;
  }
 else {
    if (mLithoView != null) {
      mLithoView.setMountStateDirty();
    }
    final LayoutState toRelease=mMainThreadLayoutState;
    mMainThreadLayoutState=mBackgroundLayoutState;
    mBackgroundLayoutState=null;
    return toRelease;
  }
}
