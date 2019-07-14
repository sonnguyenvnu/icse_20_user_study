private void onAttach(){
  if (!mIsAttached) {
    mIsAttached=true;
    if (mComponentTree != null) {
      mComponentTree.attach();
    }
    refreshAccessibilityDelegatesIfNeeded(isAccessibilityEnabled(getContext()));
    AccessibilityManagerCompat.addAccessibilityStateChangeListener(mAccessibilityManager,mAccessibilityStateChangeListener);
  }
}
