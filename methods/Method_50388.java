@Override public Object interceptor(final ProceedingJoinPoint pjp) throws Throwable {
  HmilyTransactionContext hmilyTransactionContext=RpcMediator.getInstance().acquire(RpcContext.getContext()::getAttachment);
  if (Objects.isNull(hmilyTransactionContext)) {
    hmilyTransactionContext=HmilyTransactionContextLocal.getInstance().get();
  }
  return hmilyTransactionAspectService.invoke(hmilyTransactionContext,pjp);
}
