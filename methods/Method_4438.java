@Override public boolean isPlayingAd(){
  return !shouldMaskPosition() && playbackInfo.periodId.isAd();
}
