private HmilyTransaction buildHmilyTransaction(final ProceedingJoinPoint point,final int role,final String transId){
  HmilyTransaction hmilyTransaction;
  if (StringUtils.isNoneBlank(transId)) {
    hmilyTransaction=new HmilyTransaction(transId);
  }
 else {
    hmilyTransaction=new HmilyTransaction();
  }
  hmilyTransaction.setStatus(HmilyActionEnum.PRE_TRY.getCode());
  hmilyTransaction.setRole(role);
  MethodSignature signature=(MethodSignature)point.getSignature();
  Method method=signature.getMethod();
  Class<?> clazz=point.getTarget().getClass();
  Object[] args=point.getArgs();
  final Hmily hmily=method.getAnnotation(Hmily.class);
  final PatternEnum pattern=hmily.pattern();
  hmilyTransaction.setTargetClass(clazz.getName());
  hmilyTransaction.setTargetMethod(method.getName());
  hmilyTransaction.setPattern(pattern.getCode());
  HmilyInvocation confirmInvocation=null;
  String confirmMethodName=hmily.confirmMethod();
  String cancelMethodName=hmily.cancelMethod();
  if (StringUtils.isNoneBlank(confirmMethodName)) {
    hmilyTransaction.setConfirmMethod(confirmMethodName);
    confirmInvocation=new HmilyInvocation(clazz,confirmMethodName,method.getParameterTypes(),args);
  }
  HmilyInvocation cancelInvocation=null;
  if (StringUtils.isNoneBlank(cancelMethodName)) {
    hmilyTransaction.setCancelMethod(cancelMethodName);
    cancelInvocation=new HmilyInvocation(clazz,cancelMethodName,method.getParameterTypes(),args);
  }
  final HmilyParticipant hmilyParticipant=new HmilyParticipant(hmilyTransaction.getTransId(),confirmInvocation,cancelInvocation);
  hmilyTransaction.registerParticipant(hmilyParticipant);
  return hmilyTransaction;
}
