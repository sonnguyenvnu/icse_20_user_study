@Override public Object handler(final ProceedingJoinPoint point,final HmilyTransactionContext context) throws Throwable {
  HmilyTransaction hmilyTransaction=null;
  HmilyTransaction currentTransaction;
switch (HmilyActionEnum.acquireByCode(context.getAction())) {
case TRYING:
    try {
      hmilyTransaction=hmilyTransactionExecutor.preTryParticipant(context,point);
      final Object proceed=point.proceed();
      hmilyTransaction.setStatus(HmilyActionEnum.TRYING.getCode());
      hmilyTransactionExecutor.updateStatus(hmilyTransaction);
      return proceed;
    }
 catch (    Throwable throwable) {
      hmilyTransactionExecutor.deleteTransaction(hmilyTransaction);
      throw throwable;
    }
 finally {
      HmilyTransactionContextLocal.getInstance().remove();
    }
case CONFIRMING:
  currentTransaction=HmilyTransactionGuavaCacheManager.getInstance().getHmilyTransaction(context.getTransId());
return hmilyTransactionExecutor.confirm(currentTransaction);
case CANCELING:
currentTransaction=HmilyTransactionGuavaCacheManager.getInstance().getHmilyTransaction(context.getTransId());
return hmilyTransactionExecutor.cancel(currentTransaction);
default :
break;
}
Method method=((MethodSignature)(point.getSignature())).getMethod();
return DefaultValueUtils.getDefaultValue(method.getReturnType());
}
