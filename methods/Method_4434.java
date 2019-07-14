@Override public int getCurrentWindowIndex(){
  if (shouldMaskPosition()) {
    return maskingWindowIndex;
  }
 else {
    return playbackInfo.timeline.getPeriodByUid(playbackInfo.periodId.periodUid,period).windowIndex;
  }
}
