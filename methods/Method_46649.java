@Override public int transactionState(String groupId){
  try {
    return fastStorage.getTransactionState(groupId);
  }
 catch (  FastStorageException e) {
    return -1;
  }
}
