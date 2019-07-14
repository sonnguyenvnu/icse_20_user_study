@Override public void begin(String groupId) throws TransactionException {
  try {
    dtxContextRegistry.create(groupId);
  }
 catch (  TransactionException e) {
    throw new TransactionException(e);
  }
}
