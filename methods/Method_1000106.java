@Override public void close(){
  for (  TransactionCapsule tx : tmpTransactions) {
    try {
      if (tx.getTrxTrace() != null && tx.getTrxTrace().getTimeResultType().equals(TimeResultType.NORMAL)) {
        dbManager.getRepushTransactions().put(tx);
      }
    }
 catch (    InterruptedException e) {
      logger.error(e.getMessage());
      Thread.currentThread().interrupt();
    }
  }
  tmpTransactions.clear();
  for (  TransactionCapsule tx : dbManager.getPoppedTransactions()) {
    try {
      if (tx.getTrxTrace() != null && tx.getTrxTrace().getTimeResultType().equals(TimeResultType.NORMAL)) {
        dbManager.getRepushTransactions().put(tx);
      }
    }
 catch (    InterruptedException e) {
      logger.error(e.getMessage());
      Thread.currentThread().interrupt();
    }
  }
  dbManager.getPoppedTransactions().clear();
}
