public void closeTransaction(StandardJanusGraphTx tx){
  openTransactions.remove(tx);
}
