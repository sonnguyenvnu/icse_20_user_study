private void updatePlaybackPositions() throws ExoPlaybackException {
  if (!queue.hasPlayingPeriod()) {
    return;
  }
  MediaPeriodHolder playingPeriodHolder=queue.getPlayingPeriod();
  long periodPositionUs=playingPeriodHolder.mediaPeriod.readDiscontinuity();
  if (periodPositionUs != C.TIME_UNSET) {
    resetRendererPosition(periodPositionUs);
    if (periodPositionUs != playbackInfo.positionUs) {
      playbackInfo=playbackInfo.copyWithNewPosition(playbackInfo.periodId,periodPositionUs,playbackInfo.contentPositionUs,getTotalBufferedDurationUs());
      playbackInfoUpdate.setPositionDiscontinuity(Player.DISCONTINUITY_REASON_INTERNAL);
    }
  }
 else {
    rendererPositionUs=mediaClock.syncAndGetPositionUs();
    periodPositionUs=playingPeriodHolder.toPeriodTime(rendererPositionUs);
    maybeTriggerPendingMessages(playbackInfo.positionUs,periodPositionUs);
    playbackInfo.positionUs=periodPositionUs;
  }
  MediaPeriodHolder loadingPeriod=queue.getLoadingPeriod();
  playbackInfo.bufferedPositionUs=loadingPeriod.getBufferedPositionUs();
  playbackInfo.totalBufferedDurationUs=getTotalBufferedDurationUs();
}
