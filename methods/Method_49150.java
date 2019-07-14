public StandardJanusGraphTx getNextTx(){
  Preconditions.checkArgument(isClosed());
  if (!config.isThreadBound())   throw new IllegalStateException("Cannot access element because its enclosing transaction is closed and unbound");
 else   return (StandardJanusGraphTx)graph.getCurrentThreadTx();
}
