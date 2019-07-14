@Override public long getCurrentPosition(){
  if (shouldMaskPosition()) {
    return maskingWindowPositionMs;
  }
 else   if (playbackInfo.periodId.isAd()) {
    return C.usToMs(playbackInfo.positionUs);
  }
 else {
    return periodPositionUsToWindowPositionMs(playbackInfo.periodId,playbackInfo.positionUs);
  }
}
