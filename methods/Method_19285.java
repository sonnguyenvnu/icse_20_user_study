private void requestRemeasure(){
  if (SectionsDebug.ENABLED) {
    Log.d(SectionsDebug.TAG,"(" + hashCode() + ") requestRemeasure");
  }
  if (mMountedView != null) {
    mMainThreadHandler.removeCallbacks(mRemeasureRunnable);
    mMountedView.removeCallbacks(mRemeasureRunnable);
    ViewCompat.postOnAnimation(mMountedView,mRemeasureRunnable);
  }
 else {
    mMainThreadHandler.removeCallbacks(mRemeasureRunnable);
    mMainThreadHandler.post(mRemeasureRunnable);
  }
}
