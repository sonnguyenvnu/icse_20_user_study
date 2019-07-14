public StandardJanusGraphTx newTransaction(final TransactionConfiguration configuration){
  if (!isOpen)   ExceptionFactory.graphShutdown();
  try {
    StandardJanusGraphTx tx=new StandardJanusGraphTx(this,configuration);
    tx.setBackendTransaction(openBackendTransaction(tx));
    openTransactions.add(tx);
    return tx;
  }
 catch (  BackendException e) {
    throw new JanusGraphException("Could not start new transaction",e);
  }
}
