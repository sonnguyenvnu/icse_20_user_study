@Override public boolean continueLoading(long playbackPositionUs){
  if (loadingFinished || pendingDeferredRetry || (prepared && enabledTrackCount == 0)) {
    return false;
  }
  boolean continuedLoading=loadCondition.open();
  if (!loader.isLoading()) {
    startLoading();
    continuedLoading=true;
  }
  return continuedLoading;
}
