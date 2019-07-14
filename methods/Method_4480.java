private void handleSourceInfoRefreshed(MediaSourceRefreshInfo sourceRefreshInfo) throws ExoPlaybackException {
  if (sourceRefreshInfo.source != mediaSource) {
    return;
  }
  Timeline oldTimeline=playbackInfo.timeline;
  Timeline timeline=sourceRefreshInfo.timeline;
  Object manifest=sourceRefreshInfo.manifest;
  queue.setTimeline(timeline);
  playbackInfo=playbackInfo.copyWithTimeline(timeline,manifest);
  resolvePendingMessagePositions();
  if (pendingPrepareCount > 0) {
    playbackInfoUpdate.incrementPendingOperationAcks(pendingPrepareCount);
    pendingPrepareCount=0;
    if (pendingInitialSeekPosition != null) {
      Pair<Object,Long> periodPosition;
      try {
        periodPosition=resolveSeekPosition(pendingInitialSeekPosition,true);
      }
 catch (      IllegalSeekPositionException e) {
        MediaPeriodId firstMediaPeriodId=playbackInfo.getDummyFirstMediaPeriodId(shuffleModeEnabled,window);
        playbackInfo=playbackInfo.resetToNewPosition(firstMediaPeriodId,C.TIME_UNSET,C.TIME_UNSET);
        throw e;
      }
      pendingInitialSeekPosition=null;
      if (periodPosition == null) {
        handleSourceInfoRefreshEndedPlayback();
      }
 else {
        Object periodUid=periodPosition.first;
        long positionUs=periodPosition.second;
        MediaPeriodId periodId=queue.resolveMediaPeriodIdForAds(periodUid,positionUs);
        playbackInfo=playbackInfo.resetToNewPosition(periodId,periodId.isAd() ? 0 : positionUs,positionUs);
      }
    }
 else     if (playbackInfo.startPositionUs == C.TIME_UNSET) {
      if (timeline.isEmpty()) {
        handleSourceInfoRefreshEndedPlayback();
      }
 else {
        Pair<Object,Long> defaultPosition=getPeriodPosition(timeline,timeline.getFirstWindowIndex(shuffleModeEnabled),C.TIME_UNSET);
        Object periodUid=defaultPosition.first;
        long startPositionUs=defaultPosition.second;
        MediaPeriodId periodId=queue.resolveMediaPeriodIdForAds(periodUid,startPositionUs);
        playbackInfo=playbackInfo.resetToNewPosition(periodId,periodId.isAd() ? 0 : startPositionUs,startPositionUs);
      }
    }
    return;
  }
  if (oldTimeline.isEmpty()) {
    if (!timeline.isEmpty()) {
      Pair<Object,Long> defaultPosition=getPeriodPosition(timeline,timeline.getFirstWindowIndex(shuffleModeEnabled),C.TIME_UNSET);
      Object periodUid=defaultPosition.first;
      long startPositionUs=defaultPosition.second;
      MediaPeriodId periodId=queue.resolveMediaPeriodIdForAds(periodUid,startPositionUs);
      playbackInfo=playbackInfo.resetToNewPosition(periodId,periodId.isAd() ? 0 : startPositionUs,startPositionUs);
    }
    return;
  }
  MediaPeriodHolder periodHolder=queue.getFrontPeriod();
  long contentPositionUs=playbackInfo.contentPositionUs;
  Object playingPeriodUid=periodHolder == null ? playbackInfo.periodId.periodUid : periodHolder.uid;
  int periodIndex=timeline.getIndexOfPeriod(playingPeriodUid);
  if (periodIndex == C.INDEX_UNSET) {
    Object newPeriodUid=resolveSubsequentPeriod(playingPeriodUid,oldTimeline,timeline);
    if (newPeriodUid == null) {
      handleSourceInfoRefreshEndedPlayback();
      return;
    }
    Pair<Object,Long> defaultPosition=getPeriodPosition(timeline,timeline.getPeriodByUid(newPeriodUid,period).windowIndex,C.TIME_UNSET);
    newPeriodUid=defaultPosition.first;
    contentPositionUs=defaultPosition.second;
    MediaPeriodId periodId=queue.resolveMediaPeriodIdForAds(newPeriodUid,contentPositionUs);
    if (periodHolder != null) {
      while (periodHolder.getNext() != null) {
        periodHolder=periodHolder.getNext();
        if (periodHolder.info.id.equals(periodId)) {
          periodHolder.info=queue.getUpdatedMediaPeriodInfo(periodHolder.info);
        }
      }
    }
    long seekPositionUs=seekToPeriodPosition(periodId,periodId.isAd() ? 0 : contentPositionUs);
    playbackInfo=playbackInfo.copyWithNewPosition(periodId,seekPositionUs,contentPositionUs,getTotalBufferedDurationUs());
    return;
  }
  MediaPeriodId playingPeriodId=playbackInfo.periodId;
  if (playingPeriodId.isAd()) {
    MediaPeriodId periodId=queue.resolveMediaPeriodIdForAds(playingPeriodUid,contentPositionUs);
    if (!periodId.equals(playingPeriodId)) {
      long seekPositionUs=seekToPeriodPosition(periodId,periodId.isAd() ? 0 : contentPositionUs);
      playbackInfo=playbackInfo.copyWithNewPosition(periodId,seekPositionUs,contentPositionUs,getTotalBufferedDurationUs());
      return;
    }
  }
  if (!queue.updateQueuedPeriods(playingPeriodId,rendererPositionUs)) {
    seekToCurrentPosition(false);
  }
  handleLoadingMediaPeriodChanged(false);
}
