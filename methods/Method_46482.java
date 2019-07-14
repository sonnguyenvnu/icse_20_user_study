@Override public void preBusinessCode(TxTransactionInfo info) throws TransactionException {
  try {
    globalContext.tccTransactionInfo(info.getUnitId(),() -> TccStartingTransaction.prepareTccInfo(info)).setMethodParameter(info.getTransactionInfo().getArgumentValues());
  }
 catch (  Throwable throwable) {
    throw new TransactionException(throwable);
  }
}
