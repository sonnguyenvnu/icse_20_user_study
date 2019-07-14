/** 
 * Invalidates the accessibility node tree in this host.
 */
void invalidateAccessibilityState(){
  if (!mIsComponentAccessibilityDelegateSet) {
    return;
  }
  if (mSuppressInvalidations) {
    mWasInvalidatedForAccessibilityWhileSuppressed=true;
    return;
  }
  if (mComponentAccessibilityDelegate != null && implementsVirtualViews()) {
    mComponentAccessibilityDelegate.invalidateRoot();
  }
}
