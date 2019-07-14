private void onAdSourceInfoRefreshed(MediaSource mediaSource,int adGroupIndex,int adIndexInAdGroup,Timeline timeline){
  Assertions.checkArgument(timeline.getPeriodCount() == 1);
  adGroupTimelines[adGroupIndex][adIndexInAdGroup]=timeline;
  List<DeferredMediaPeriod> mediaPeriods=deferredMediaPeriodByAdMediaSource.remove(mediaSource);
  if (mediaPeriods != null) {
    Object periodUid=timeline.getUidOfPeriod(0);
    for (int i=0; i < mediaPeriods.size(); i++) {
      DeferredMediaPeriod mediaPeriod=mediaPeriods.get(i);
      MediaPeriodId adSourceMediaPeriodId=new MediaPeriodId(periodUid,mediaPeriod.id.windowSequenceNumber);
      mediaPeriod.createPeriod(adSourceMediaPeriodId);
    }
  }
  maybeUpdateSourceInfo();
}
