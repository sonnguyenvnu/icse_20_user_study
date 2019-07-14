private long periodPositionUsToWindowPositionMs(MediaPeriodId periodId,long positionUs){
  long positionMs=C.usToMs(positionUs);
  playbackInfo.timeline.getPeriodByUid(periodId.periodUid,period);
  positionMs+=period.getPositionInWindowMs();
  return positionMs;
}
