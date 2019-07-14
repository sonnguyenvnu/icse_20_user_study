@Override public void releasePeriod(MediaPeriod mediaPeriod){
  DeferredMediaPeriod deferredMediaPeriod=(DeferredMediaPeriod)mediaPeriod;
  List<DeferredMediaPeriod> mediaPeriods=deferredMediaPeriodByAdMediaSource.get(deferredMediaPeriod.mediaSource);
  if (mediaPeriods != null) {
    mediaPeriods.remove(deferredMediaPeriod);
  }
  deferredMediaPeriod.releasePeriod();
}
