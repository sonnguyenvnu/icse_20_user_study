@Override public TccTransactionInfo tccTransactionInfo(String unitId,Supplier<TccTransactionInfo,TransactionException> supplier) throws TransactionException {
  String unitTransactionInfoKey=unitId + ".tcc.transaction";
  if (Objects.isNull(supplier)) {
    return attachmentCache.attachment(unitTransactionInfoKey);
  }
  if (attachmentCache.containsKey(unitTransactionInfoKey)) {
    return attachmentCache.attachment(unitTransactionInfoKey);
  }
  TccTransactionInfo tccTransactionInfo=supplier.get();
  attachmentCache.attach(unitTransactionInfoKey,tccTransactionInfo);
  return tccTransactionInfo;
}
