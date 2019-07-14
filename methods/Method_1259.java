@VisibleForTesting public void reportViewVisible(long now){
  mImagePerfState.setVisible(true);
  mImagePerfState.setVisibilityEventTimeMs(now);
  mImagePerfMonitor.notifyListenersOfVisibilityStateUpdate(mImagePerfState,VisibilityState.VISIBLE);
}
