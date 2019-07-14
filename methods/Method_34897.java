public long getNumberOfProcessedPages(){
  return counters.getValue(Counters.ReservedCounterNames.PROCESSED_PAGES);
}
