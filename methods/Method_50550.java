@Override public Object interceptor(final ProceedingJoinPoint pjp) throws Throwable {
  HmilyTransactionContext hmilyTransactionContext=HmilyTransactionContextLocal.getInstance().get();
  if (Objects.nonNull(hmilyTransactionContext)) {
    if (HmilyRoleEnum.START.getCode() == hmilyTransactionContext.getRole()) {
      hmilyTransactionContext.setRole(HmilyRoleEnum.SPRING_CLOUD.getCode());
    }
  }
 else {
    try {
      final RequestAttributes requestAttributes=RequestContextHolder.currentRequestAttributes();
      hmilyTransactionContext=RpcMediator.getInstance().acquire(key -> {
        HttpServletRequest request=((ServletRequestAttributes)requestAttributes).getRequest();
        return request.getHeader(key);
      }
);
    }
 catch (    IllegalStateException ex) {
      LogUtil.warn(LOGGER,() -> "can not acquire request info:" + ex.getLocalizedMessage());
    }
  }
  return hmilyTransactionAspectService.invoke(hmilyTransactionContext,pjp);
}
