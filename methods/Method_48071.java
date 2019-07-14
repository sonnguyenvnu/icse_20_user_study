private List<KeySlice> getKeySlice(ByteBuffer startKey,ByteBuffer endKey,SliceQuery columnSlice,int count) throws BackendException {
  return getRangeSlices(new org.apache.cassandra.thrift.KeyRange().setStart_key(startKey).setEnd_key(endKey).setCount(count),columnSlice);
}
