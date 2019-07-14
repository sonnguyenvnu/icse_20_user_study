@Override public Object handler(final ProceedingJoinPoint point,final HmilyTransactionContext context) throws Throwable {
  Object returnValue;
  try {
    HmilyTransaction hmilyTransaction=hmilyTransactionExecutor.preTry(point);
    try {
      returnValue=point.proceed();
      hmilyTransaction.setStatus(HmilyActionEnum.TRYING.getCode());
      hmilyTransactionExecutor.updateStatus(hmilyTransaction);
    }
 catch (    Throwable throwable) {
      final HmilyTransaction currentTransaction=hmilyTransactionExecutor.getCurrentTransaction();
      disruptorProviderManage.getProvider().onData(() -> hmilyTransactionExecutor.cancel(currentTransaction));
      throw throwable;
    }
    final HmilyTransaction currentTransaction=hmilyTransactionExecutor.getCurrentTransaction();
    disruptorProviderManage.getProvider().onData(() -> hmilyTransactionExecutor.confirm(currentTransaction));
  }
  finally {
    HmilyTransactionContextLocal.getInstance().remove();
    hmilyTransactionExecutor.remove();
  }
  return returnValue;
}
