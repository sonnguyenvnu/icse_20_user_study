@VisibleForTesting private void reportViewInvisible(long time){
  mImagePerfState.setVisible(false);
  mImagePerfState.setInvisibilityEventTimeMs(time);
  mImagePerfMonitor.notifyListenersOfVisibilityStateUpdate(mImagePerfState,VisibilityState.INVISIBLE);
}
