public RecordIterator<Long> getVertexIDs(final BackendTransaction tx){
  Preconditions.checkArgument(backend.getStoreFeatures().hasOrderedScan() || backend.getStoreFeatures().hasUnorderedScan(),"The configured storage backend does not support global graph operations - use Faunus instead");
  final KeyIterator keyIterator;
  if (backend.getStoreFeatures().hasUnorderedScan()) {
    keyIterator=tx.edgeStoreKeys(vertexExistenceQuery);
  }
 else {
    keyIterator=tx.edgeStoreKeys(new KeyRangeQuery(IDHandler.MIN_KEY,IDHandler.MAX_KEY,vertexExistenceQuery));
  }
  return new RecordIterator<Long>(){
    @Override public boolean hasNext(){
      return keyIterator.hasNext();
    }
    @Override public Long next(){
      return idManager.getKeyID(keyIterator.next());
    }
    @Override public void close() throws IOException {
      keyIterator.close();
    }
    @Override public void remove(){
      throw new UnsupportedOperationException("Removal not supported");
    }
  }
;
}
