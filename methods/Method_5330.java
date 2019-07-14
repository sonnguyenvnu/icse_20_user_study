@Override public void onSourceInfoRefreshed(long durationUs,boolean isSeekable){
  durationUs=durationUs == C.TIME_UNSET ? timelineDurationUs : durationUs;
  if (timelineDurationUs == durationUs && timelineIsSeekable == isSeekable) {
    return;
  }
  notifySourceInfoRefreshed(durationUs,isSeekable);
}
