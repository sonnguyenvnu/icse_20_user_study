private long getCurrentID(final StaticBuffer partitionKey) throws BackendException {
  final List<Entry> blocks=BackendOperation.execute((BackendOperation.Transactional<List<Entry>>)txh -> idStore.getSlice(new KeySliceQuery(partitionKey,LOWER_SLICE,UPPER_SLICE).setLimit(5),txh),this,times);
  if (blocks == null)   throw new TemporaryBackendException("Could not read from storage");
  long latest=BASE_ID;
  for (  Entry e : blocks) {
    long counterVal=getBlockValue(e);
    if (latest < counterVal) {
      latest=counterVal;
    }
  }
  return latest;
}
