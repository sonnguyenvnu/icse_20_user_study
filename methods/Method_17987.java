@GuardedBy("this") private boolean hasCompatibleComponentAndSpec(){
  assertHoldsLock(this);
  return isCompatibleComponentAndSpec(mMainThreadLayoutState) || isCompatibleComponentAndSpec(mBackgroundLayoutState);
}
