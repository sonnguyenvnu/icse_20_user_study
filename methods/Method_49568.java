@Override public KeyIterator getKeys(SliceQuery query,StoreTransaction txh) throws BackendException {
  return executeKeySliceQuery(new FilterList(FilterList.Operator.MUST_PASS_ALL),query);
}
