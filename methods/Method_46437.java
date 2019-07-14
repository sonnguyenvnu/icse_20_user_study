@Around("txcTransactionPointcut() && !lcnTransactionPointcut()" + "&& !tccTransactionPointcut() && !txTransactionPointcut()") public Object runWithTxcTransaction(ProceedingJoinPoint point) throws Throwable {
  DTXInfo dtxInfo=DTXInfo.getFromCache(point);
  TxcTransaction txcTransaction=dtxInfo.getBusinessMethod().getAnnotation(TxcTransaction.class);
  dtxInfo.setTransactionType(Transactions.TXC);
  dtxInfo.setTransactionPropagation(txcTransaction.propagation());
  return dtxLogicWeaver.runTransaction(dtxInfo,point::proceed);
}
