/** 
 * Create a RangeSliceCommand and run it against the StorageProxy. <p> To match the behavior of the standard Cassandra thrift API endpoint, the {@code nowMillis} argument should be the number of milliseconds since theUNIX Epoch (e.g. System.currentTimeMillis() or equivalent obtained through a  {@link TimestampProvider}).
 */
private List<Row> getKeySlice(Token start,Token end,@Nullable SliceQuery sliceQuery,int pageSize,long nowMillis) throws BackendException {
  SliceRange columnSlice=new SliceRange();
  if (sliceQuery == null) {
    columnSlice.setStart(ArrayUtils.EMPTY_BYTE_ARRAY).setFinish(ArrayUtils.EMPTY_BYTE_ARRAY).setCount(5);
  }
 else {
    columnSlice.setStart(sliceQuery.getSliceStart().asByteBuffer()).setFinish(sliceQuery.getSliceEnd().asByteBuffer()).setCount(sliceQuery.hasLimit() ? sliceQuery.getLimit() : Integer.MAX_VALUE);
  }
  SlicePredicate predicate=new SlicePredicate().setSlice_range(columnSlice);
  RowPosition startPosition=start.minKeyBound();
  RowPosition endPosition=end.minKeyBound();
  List<Row> rows;
  try {
    CFMetaData cfm=Schema.instance.getCFMetaData(keyspace,columnFamily);
    IDiskAtomFilter filter=ThriftValidation.asIFilter(predicate,cfm,null);
    final RangeSliceCommand cmd=new RangeSliceCommand(keyspace,columnFamily,nowMillis,filter,new Bounds<>(startPosition,endPosition),pageSize);
    rows=StorageProxy.getRangeSlice(cmd,ConsistencyLevel.QUORUM);
  }
 catch (  Exception e) {
    throw new PermanentBackendException(e);
  }
  return rows;
}
