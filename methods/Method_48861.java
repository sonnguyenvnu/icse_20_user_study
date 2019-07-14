public static StandardJanusGraphTx startTransaction(StandardJanusGraph graph){
  StandardTransactionBuilder txb=graph.buildTransaction().readOnly();
  txb.setPreloadedData(true);
  txb.checkInternalVertexExistence(false);
  txb.dirtyVertexSize(0);
  txb.vertexCacheSize(0);
  return (StandardJanusGraphTx)txb.start();
}
