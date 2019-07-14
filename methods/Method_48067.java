@Override public KeyIterator getKeys(KeyRangeQuery keyRangeQuery,StoreTransaction txh) throws BackendException {
  final IPartitioner partitioner=storeManager.getCassandraPartitioner();
  if (!(partitioner instanceof ByteOrderedPartitioner))   throw new PermanentBackendException("This operation is only allowed when byte-ordered partitioner is used.");
  try {
    return new KeyRangeIterator(partitioner,keyRangeQuery,storeManager.getPageSize(),keyRangeQuery.getKeyStart().asByteBuffer(),keyRangeQuery.getKeyEnd().asByteBuffer());
  }
 catch (  Exception e) {
    throw convertException(e);
  }
}
