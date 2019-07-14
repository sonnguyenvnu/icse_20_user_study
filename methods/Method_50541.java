@Override public Object interceptor(final ProceedingJoinPoint pjp) throws Throwable {
  HmilyTransactionContext hmilyTransactionContext=RpcMediator.getInstance().acquire(key -> {
    final Request request=RpcContext.getContext().getRequest();
    if (Objects.nonNull(request)) {
      final Map<String,String> attachments=request.getAttachments();
      if (attachments != null && !attachments.isEmpty()) {
        return attachments.get(key);
      }
    }
    return "";
  }
);
  if (Objects.isNull(hmilyTransactionContext)) {
    hmilyTransactionContext=HmilyTransactionContextLocal.getInstance().get();
  }
  return hmilyTransactionAspectService.invoke(hmilyTransactionContext,pjp);
}
