private long seekToPeriodPosition(MediaPeriodId periodId,long periodPositionUs) throws ExoPlaybackException {
  return seekToPeriodPosition(periodId,periodPositionUs,queue.getPlayingPeriod() != queue.getReadingPeriod());
}
