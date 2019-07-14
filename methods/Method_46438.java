@Around("tccTransactionPointcut() && !lcnTransactionPointcut()" + "&& !txcTransactionPointcut() && !txTransactionPointcut()") public Object runWithTccTransaction(ProceedingJoinPoint point) throws Throwable {
  DTXInfo dtxInfo=DTXInfo.getFromCache(point);
  TccTransaction tccTransaction=dtxInfo.getBusinessMethod().getAnnotation(TccTransaction.class);
  dtxInfo.setTransactionType(Transactions.TCC);
  dtxInfo.setTransactionPropagation(tccTransaction.propagation());
  return dtxLogicWeaver.runTransaction(dtxInfo,point::proceed);
}
