public long getNumberOfScheduledPages(){
  return counters.getValue(Counters.ReservedCounterNames.SCHEDULED_PAGES);
}
