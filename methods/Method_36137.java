@Override public Optional<Integer> maxRequestJournalEntries(){
  if (specifiesMaxRequestJournalEntries()) {
    return Optional.of(Integer.parseInt((String)optionSet.valueOf(MAX_ENTRIES_REQUEST_JOURNAL)));
  }
  return Optional.absent();
}
