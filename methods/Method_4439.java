@Override public long getContentPosition(){
  if (isPlayingAd()) {
    playbackInfo.timeline.getPeriodByUid(playbackInfo.periodId.periodUid,period);
    return period.getPositionInWindowMs() + C.usToMs(playbackInfo.contentPositionUs);
  }
 else {
    return getCurrentPosition();
  }
}
