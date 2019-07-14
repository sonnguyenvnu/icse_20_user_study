@Around("lcnTransactionPointcut() && !txcTransactionPointcut()" + "&& !tccTransactionPointcut() && !txTransactionPointcut()") public Object runWithLcnTransaction(ProceedingJoinPoint point) throws Throwable {
  DTXInfo dtxInfo=DTXInfo.getFromCache(point);
  LcnTransaction lcnTransaction=dtxInfo.getBusinessMethod().getAnnotation(LcnTransaction.class);
  dtxInfo.setTransactionType(Transactions.LCN);
  dtxInfo.setTransactionPropagation(lcnTransaction.propagation());
  return dtxLogicWeaver.runTransaction(dtxInfo,point::proceed);
}
