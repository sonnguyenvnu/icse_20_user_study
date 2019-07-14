public static DTXInfo getFromCache(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
  String signature=proceedingJoinPoint.getSignature().toString();
  String unitId=Transactions.unitId(signature);
  DTXInfo dtxInfo=dtxInfoCache.get(unitId);
  if (Objects.isNull(dtxInfo)) {
    MethodSignature methodSignature=(MethodSignature)proceedingJoinPoint.getSignature();
    Method method=methodSignature.getMethod();
    Class<?> targetClass=proceedingJoinPoint.getTarget().getClass();
    Method thisMethod=targetClass.getMethod(method.getName(),method.getParameterTypes());
    dtxInfo=new DTXInfo(thisMethod,proceedingJoinPoint.getArgs(),targetClass);
    dtxInfoCache.put(unitId,dtxInfo);
  }
  dtxInfo.reanalyseMethodArgs(proceedingJoinPoint.getArgs());
  return dtxInfo;
}
