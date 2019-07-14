@Override public void resetTransactionState(int state) throws TransactionException {
  try {
    fastStorage.saveTransactionState(groupId,state);
  }
 catch (  FastStorageException e) {
    throw new TransactionException(e);
  }
}
