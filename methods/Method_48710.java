private BackendTransaction openBackendTransaction(StandardJanusGraphTx tx) throws BackendException {
  IndexSerializer.IndexInfoRetriever retriever=indexSerializer.getIndexInfoRetriever(tx);
  return backend.beginTransaction(tx.getConfiguration(),retriever);
}
