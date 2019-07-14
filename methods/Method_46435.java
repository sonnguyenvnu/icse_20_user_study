@Around("txTransactionPointcut()") public Object transactionRunning(ProceedingJoinPoint point) throws Throwable {
  DTXInfo dtxInfo=DTXInfo.getFromCache(point);
  TxTransaction txTransaction=dtxInfo.getBusinessMethod().getAnnotation(TxTransaction.class);
  dtxInfo.setTransactionType(txTransaction.type());
  dtxInfo.setTransactionPropagation(txTransaction.propagation());
  return dtxLogicWeaver.runTransaction(dtxInfo,point::proceed);
}
