@Override public void preBusinessCode(TxTransactionInfo info) throws TransactionException {
  try {
    transactionControlTemplate.createGroup(info.getGroupId(),info.getUnitId(),info.getTransactionInfo(),info.getTransactionType());
  }
 catch (  Exception e) {
    throw new TransactionException(e);
  }
  DTXLocalContext.makeProxy();
}
