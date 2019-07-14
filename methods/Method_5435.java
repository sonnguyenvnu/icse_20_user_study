@Override protected @Nullable MediaPeriodId getMediaPeriodIdForChildMediaPeriodId(Void id,MediaPeriodId mediaPeriodId){
  return loopCount != Integer.MAX_VALUE ? childMediaPeriodIdToMediaPeriodId.get(mediaPeriodId) : mediaPeriodId;
}
