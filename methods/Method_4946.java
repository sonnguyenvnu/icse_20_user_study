private boolean isLastInPeriod(MediaPeriodId id){
  return !id.isAd() && id.endPositionUs == C.TIME_UNSET;
}
