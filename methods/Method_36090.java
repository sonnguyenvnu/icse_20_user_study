@Override public Optional<Integer> maxRequestJournalEntries(){
  String str=servletContext.getInitParameter("maxRequestJournalEntries");
  if (str == null) {
    return Optional.absent();
  }
  return Optional.of(Integer.parseInt(str));
}
