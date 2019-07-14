@Override public void releasePeriod(MediaPeriod mediaPeriod){
  childSource.releasePeriod(mediaPeriod);
  MediaPeriodId childMediaPeriodId=mediaPeriodToChildMediaPeriodId.remove(mediaPeriod);
  if (childMediaPeriodId != null) {
    childMediaPeriodIdToMediaPeriodId.remove(childMediaPeriodId);
  }
}
