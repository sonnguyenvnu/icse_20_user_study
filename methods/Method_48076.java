@Override public List<KeyRange> getLocalKeyPartition() throws BackendException {
  CTConnection conn=null;
  IPartitioner partitioner=getCassandraPartitioner();
  if (!(partitioner instanceof ByteOrderedPartitioner))   throw new UnsupportedOperationException("getLocalKeyPartition() only supported by byte ordered partitioner.");
  Token.TokenFactory tokenFactory=partitioner.getTokenFactory();
  try {
    ensureKeyspaceExists(keySpaceName);
    conn=pool.borrowObject(keySpaceName);
    final List<TokenRange> ranges=conn.getClient().describe_ring(keySpaceName);
    final List<KeyRange> keyRanges=new ArrayList<>(ranges.size());
    for (    TokenRange range : ranges) {
      if (!NetworkUtil.hasLocalAddress(range.endpoints))       continue;
      keyRanges.add(CassandraHelper.transformRange(tokenFactory.fromString(range.start_token),tokenFactory.fromString(range.end_token)));
    }
    return keyRanges;
  }
 catch (  Exception e) {
    throw CassandraThriftKeyColumnValueStore.convertException(e);
  }
 finally {
    pool.returnObjectUnsafe(keySpaceName,conn);
  }
}
