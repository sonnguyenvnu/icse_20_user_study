public Entry snapshot(final Snapshot snapshot){
  for (  Entry entry : entries) {
    final Snapshot curr=entry.snapshot();
    if (curr.equals(snapshot)) {
      return entry;
    }
  }
  return null;
}
