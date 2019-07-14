private void onDetach(){
  if (mIsAttached) {
    mIsAttached=false;
    mMountState.detach();
    if (mComponentTree != null) {
      mComponentTree.detach();
    }
    AccessibilityManagerCompat.removeAccessibilityStateChangeListener(mAccessibilityManager,mAccessibilityStateChangeListener);
    mSuppressMeasureComponentTree=false;
  }
}
