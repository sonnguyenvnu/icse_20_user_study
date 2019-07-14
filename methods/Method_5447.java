private IllegalMergeException checkTimelineMerges(Timeline timeline){
  if (periodCount == PERIOD_COUNT_UNSET) {
    periodCount=timeline.getPeriodCount();
  }
 else   if (timeline.getPeriodCount() != periodCount) {
    return new IllegalMergeException(IllegalMergeException.REASON_PERIOD_COUNT_MISMATCH);
  }
  return null;
}
