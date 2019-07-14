private void maybePostUpdateViewportAndComputeRange(){
  if (mMountedView != null && mViewportManager.shouldUpdate()) {
    mMountedView.removeCallbacks(mUpdateViewportRunnable);
    ViewCompat.postOnAnimation(mMountedView,mUpdateViewportRunnable);
  }
  computeRange(mCurrentFirstVisiblePosition,mCurrentLastVisiblePosition);
}
