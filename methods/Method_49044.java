private JanusGraphBlueprintsTransaction getAutoStartTx(){
  if (txs == null)   throw new IllegalStateException("Graph has been closed");
  tinkerpopTxContainer.readWrite();
  JanusGraphBlueprintsTransaction tx=txs.get();
  Preconditions.checkNotNull(tx,"Invalid read-write behavior configured: " + "Should either open transaction or throw exception.");
  return tx;
}
