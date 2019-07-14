private void seekToInternal(SeekPosition seekPosition) throws ExoPlaybackException {
  playbackInfoUpdate.incrementPendingOperationAcks(1);
  MediaPeriodId periodId;
  long periodPositionUs;
  long contentPositionUs;
  boolean seekPositionAdjusted;
  Pair<Object,Long> resolvedSeekPosition=resolveSeekPosition(seekPosition,true);
  if (resolvedSeekPosition == null) {
    periodId=playbackInfo.getDummyFirstMediaPeriodId(shuffleModeEnabled,window);
    periodPositionUs=C.TIME_UNSET;
    contentPositionUs=C.TIME_UNSET;
    seekPositionAdjusted=true;
  }
 else {
    Object periodUid=resolvedSeekPosition.first;
    contentPositionUs=resolvedSeekPosition.second;
    periodId=queue.resolveMediaPeriodIdForAds(periodUid,contentPositionUs);
    if (periodId.isAd()) {
      periodPositionUs=0;
      seekPositionAdjusted=true;
    }
 else {
      periodPositionUs=resolvedSeekPosition.second;
      seekPositionAdjusted=seekPosition.windowPositionUs == C.TIME_UNSET;
    }
  }
  try {
    if (mediaSource == null || pendingPrepareCount > 0) {
      pendingInitialSeekPosition=seekPosition;
    }
 else     if (periodPositionUs == C.TIME_UNSET) {
      setState(Player.STATE_ENDED);
      resetInternal(false,false,true,false);
    }
 else {
      long newPeriodPositionUs=periodPositionUs;
      if (periodId.equals(playbackInfo.periodId)) {
        MediaPeriodHolder playingPeriodHolder=queue.getPlayingPeriod();
        if (playingPeriodHolder != null && newPeriodPositionUs != 0) {
          newPeriodPositionUs=playingPeriodHolder.mediaPeriod.getAdjustedSeekPositionUs(newPeriodPositionUs,seekParameters);
        }
        if (C.usToMs(newPeriodPositionUs) == C.usToMs(playbackInfo.positionUs)) {
          periodPositionUs=playbackInfo.positionUs;
          return;
        }
      }
      newPeriodPositionUs=seekToPeriodPosition(periodId,newPeriodPositionUs);
      seekPositionAdjusted|=periodPositionUs != newPeriodPositionUs;
      periodPositionUs=newPeriodPositionUs;
    }
  }
  finally {
    playbackInfo=playbackInfo.copyWithNewPosition(periodId,periodPositionUs,contentPositionUs,getTotalBufferedDurationUs());
    if (seekPositionAdjusted) {
      playbackInfoUpdate.setPositionDiscontinuity(Player.DISCONTINUITY_REASON_SEEK_ADJUSTMENT);
    }
  }
}
