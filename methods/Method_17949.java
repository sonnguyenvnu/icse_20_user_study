@CheckReturnValue @ReturnsOwnership @ThreadConfined(ThreadConfined.UI) @GuardedBy("this") private boolean isBestMainThreadLayout(){
  assertHoldsLock(this);
  if (isCompatibleComponentAndSpec(mMainThreadLayoutState)) {
    return true;
  }
 else   if (isCompatibleSpec(mBackgroundLayoutState,mWidthSpec,mHeightSpec) || !isCompatibleSpec(mMainThreadLayoutState,mWidthSpec,mHeightSpec)) {
    return false;
  }
 else {
    return true;
  }
}
