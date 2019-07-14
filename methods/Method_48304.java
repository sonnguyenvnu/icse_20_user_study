@Override public void mutate(StaticBuffer key,List<Entry> additions,List<StaticBuffer> deletions,StoreTransaction txh) throws BackendException {
  throw new UnsupportedOperationException("Cannot mutate a read-only store");
}
