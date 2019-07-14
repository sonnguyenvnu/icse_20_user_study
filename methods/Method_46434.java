static DTXInfo load(MethodInvocation invocation,Properties transactionAttributes){
  String transactionType=Transactions.LCN;
  DTXPropagation dtxPropagation=DTXPropagation.REQUIRED;
  if (transactionAttributes != null) {
    transactionType=transactionAttributes.getProperty(Transactions.DTX_TYPE);
    dtxPropagation=DTXPropagation.parser(transactionAttributes.getProperty(Transactions.DTX_PROPAGATION));
  }
  TxTransaction txTransaction=invocation.getMethod().getAnnotation(TxTransaction.class);
  if (Objects.nonNull(txTransaction)) {
    transactionType=txTransaction.type();
    dtxPropagation=txTransaction.propagation();
  }
 else {
    LcnTransaction lcnTransaction=invocation.getMethod().getAnnotation(LcnTransaction.class);
    if (Objects.nonNull(lcnTransaction)) {
      transactionType=Transactions.LCN;
      dtxPropagation=lcnTransaction.propagation();
    }
 else {
      TxcTransaction txcTransaction=invocation.getMethod().getAnnotation(TxcTransaction.class);
      if (Objects.nonNull(txcTransaction)) {
        transactionType=Transactions.TXC;
        dtxPropagation=txcTransaction.propagation();
      }
 else {
        TccTransaction tccTransaction=invocation.getMethod().getAnnotation(TccTransaction.class);
        if (Objects.nonNull(tccTransaction)) {
          transactionType=Transactions.TCC;
          dtxPropagation=tccTransaction.propagation();
        }
      }
    }
  }
  DTXInfo dtxInfo=DTXInfo.getFromCache(invocation);
  dtxInfo.setTransactionType(transactionType);
  dtxInfo.setTransactionPropagation(dtxPropagation);
  return dtxInfo;
}
