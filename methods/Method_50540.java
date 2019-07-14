@SuppressWarnings("unchecked") private HmilyParticipant buildParticipant(final HmilyTransactionContext hmilyTransactionContext,final Hmily hmily,final Method method,final Class clazz,final Object[] arguments,final Class... args) throws HmilyRuntimeException {
  if (Objects.isNull(hmilyTransactionContext) || (HmilyActionEnum.TRYING.getCode() != hmilyTransactionContext.getAction())) {
    return null;
  }
  String confirmMethodName=hmily.confirmMethod();
  if (StringUtils.isBlank(confirmMethodName)) {
    confirmMethodName=method.getName();
  }
  String cancelMethodName=hmily.cancelMethod();
  if (StringUtils.isBlank(cancelMethodName)) {
    cancelMethodName=method.getName();
  }
  final PatternEnum pattern=hmily.pattern();
  SpringBeanUtils.getInstance().getBean(HmilyTransactionExecutor.class).getCurrentTransaction().setPattern(pattern.getCode());
  HmilyInvocation confirmInvocation=new HmilyInvocation(clazz,confirmMethodName,args,arguments);
  HmilyInvocation cancelInvocation=new HmilyInvocation(clazz,cancelMethodName,args,arguments);
  return new HmilyParticipant(hmilyTransactionContext.getTransId(),confirmInvocation,cancelInvocation);
}
