static TccTransactionInfo prepareTccInfo(TxTransactionInfo info) throws TransactionException {
  Method method=info.getPointMethod();
  TccTransaction tccTransaction=method.getAnnotation(TccTransaction.class);
  if (tccTransaction == null) {
    throw new TransactionException("TCC type need @TccTransaction in " + method.getName());
  }
  String cancelMethod=tccTransaction.cancelMethod();
  String confirmMethod=tccTransaction.confirmMethod();
  Class<?> executeClass=tccTransaction.executeClass();
  if (StringUtils.isEmpty(tccTransaction.cancelMethod())) {
    cancelMethod="cancel" + StringUtils.capitalize(method.getName());
  }
  if (StringUtils.isEmpty(tccTransaction.confirmMethod())) {
    confirmMethod="confirm" + StringUtils.capitalize(method.getName());
  }
  if (Void.class.isAssignableFrom(executeClass)) {
    executeClass=info.getTransactionInfo().getTargetClazz();
  }
  TccTransactionInfo tccInfo=new TccTransactionInfo();
  tccInfo.setExecuteClass(executeClass);
  tccInfo.setCancelMethod(cancelMethod);
  tccInfo.setConfirmMethod(confirmMethod);
  tccInfo.setMethodParameter(info.getTransactionInfo().getArgumentValues());
  tccInfo.setMethodTypeParameter(info.getTransactionInfo().getParameterTypes());
  return tccInfo;
}
