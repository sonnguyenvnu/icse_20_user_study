@Override public long getBufferedPosition(){
  if (isPlayingAd()) {
    return playbackInfo.loadingMediaPeriodId.equals(playbackInfo.periodId) ? C.usToMs(playbackInfo.bufferedPositionUs) : getDuration();
  }
  return getContentBufferedPosition();
}
