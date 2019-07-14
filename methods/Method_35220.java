void ensureValidIndex(@NonNull TransactionIndexer indexer){
  if (transactionIndex == INVALID_INDEX) {
    transactionIndex=indexer.nextIndex();
  }
}
