@Override public void join(TransactionUnit transactionUnit) throws TransactionException {
  try {
    fastStorage.saveTransactionUnitToGroup(groupId,transactionUnit);
  }
 catch (  FastStorageException e) {
    throw new TransactionException("attempts to join the non-existent transaction group. [" + transactionUnit.getUnitId() + '@' + transactionUnit.getModId() + ']');
  }
}
