/** 
 * This is used to collapse all invalidation calls on hosts during mount. While invalidations are suppressed, the hosts will simply bail on invalidations. Once the suppression is turned off, a single invalidation will be triggered on the affected hosts.
 */
void suppressInvalidations(boolean suppressInvalidations){
  if (mSuppressInvalidations == suppressInvalidations) {
    return;
  }
  mSuppressInvalidations=suppressInvalidations;
  if (!mSuppressInvalidations) {
    if (mWasInvalidatedWhileSuppressed) {
      this.invalidate();
      mWasInvalidatedWhileSuppressed=false;
    }
    if (mWasInvalidatedForAccessibilityWhileSuppressed) {
      this.invalidateAccessibilityState();
      mWasInvalidatedForAccessibilityWhileSuppressed=false;
    }
    if (mWasRequestedFocusWhileSuppressed) {
      final View root=getRootView();
      if (root != null) {
        root.requestFocus();
      }
      mWasRequestedFocusWhileSuppressed=false;
    }
  }
}
