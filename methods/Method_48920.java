@Override public Iterator<R> getNew(final Q query){
  return runWithMetrics("getNew",v -> qe.getNew(query));
}
