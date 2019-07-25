public synchronized void dispose(){
  if (mDestroyed) {
    return;
  }
  ReactContext reactContext=(ReactContext)mContext;
  reactContext.removeLifecycleEventListener(mLifeCycleListener);
  if (mLocationLayer != null) {
    mLocationLayer.onStop();
  }
  mLocationManger.dispose();
  if (!mPaused) {
    onPause();
  }
  onStop();
  onDestroy();
}
