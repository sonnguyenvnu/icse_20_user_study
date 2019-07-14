private <T extends Token>List<KeySlice> getTokenSlice(T startToken,T endToken,SliceQuery sliceQuery,int count) throws BackendException {
  String st=sanitizeBrokenByteToken(startToken);
  String et=sanitizeBrokenByteToken(endToken);
  org.apache.cassandra.thrift.KeyRange kr=new org.apache.cassandra.thrift.KeyRange().setStart_token(st).setEnd_token(et).setCount(count);
  return getRangeSlices(kr,sliceQuery);
}
