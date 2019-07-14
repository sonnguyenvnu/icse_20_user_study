private void handleLoadingMediaPeriodChanged(boolean loadingTrackSelectionChanged){
  MediaPeriodHolder loadingMediaPeriodHolder=queue.getLoadingPeriod();
  MediaPeriodId loadingMediaPeriodId=loadingMediaPeriodHolder == null ? playbackInfo.periodId : loadingMediaPeriodHolder.info.id;
  boolean loadingMediaPeriodChanged=!playbackInfo.loadingMediaPeriodId.equals(loadingMediaPeriodId);
  if (loadingMediaPeriodChanged) {
    playbackInfo=playbackInfo.copyWithLoadingMediaPeriodId(loadingMediaPeriodId);
  }
  playbackInfo.bufferedPositionUs=loadingMediaPeriodHolder == null ? playbackInfo.positionUs : loadingMediaPeriodHolder.getBufferedPositionUs();
  playbackInfo.totalBufferedDurationUs=getTotalBufferedDurationUs();
  if ((loadingMediaPeriodChanged || loadingTrackSelectionChanged) && loadingMediaPeriodHolder != null && loadingMediaPeriodHolder.prepared) {
    updateLoadControlTrackSelection(loadingMediaPeriodHolder.getTrackGroups(),loadingMediaPeriodHolder.getTrackSelectorResult());
  }
}
