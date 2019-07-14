private boolean mountComponentIfNeeded(){
  if (mLithoView.isMountStateDirty() || mLithoView.mountStateNeedsRemount()) {
    if (mIncrementalMountEnabled) {
      incrementalMountComponent();
    }
 else {
      mountComponent(null,true);
    }
    return true;
  }
  return false;
}
