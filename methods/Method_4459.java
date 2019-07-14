private void seekToCurrentPosition(boolean sendDiscontinuity) throws ExoPlaybackException {
  MediaPeriodId periodId=queue.getPlayingPeriod().info.id;
  long newPositionUs=seekToPeriodPosition(periodId,playbackInfo.positionUs,true);
  if (newPositionUs != playbackInfo.positionUs) {
    playbackInfo=playbackInfo.copyWithNewPosition(periodId,newPositionUs,playbackInfo.contentPositionUs,getTotalBufferedDurationUs());
    if (sendDiscontinuity) {
      playbackInfoUpdate.setPositionDiscontinuity(Player.DISCONTINUITY_REASON_INTERNAL);
    }
  }
}
