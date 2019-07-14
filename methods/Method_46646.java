@Override public int transactionState(){
  try {
    return fastStorage.getTransactionState(groupId);
  }
 catch (  FastStorageException e) {
    return -1;
  }
}
