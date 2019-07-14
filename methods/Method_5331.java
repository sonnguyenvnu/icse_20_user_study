private void notifySourceInfoRefreshed(long durationUs,boolean isSeekable){
  timelineDurationUs=durationUs;
  timelineIsSeekable=isSeekable;
  refreshSourceInfo(new SinglePeriodTimeline(timelineDurationUs,timelineIsSeekable,false,tag),null);
}
