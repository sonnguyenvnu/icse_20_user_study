@Around("execution(* *..controller..*.*(..))") public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
  RequestAttributes requestAttributes=RequestContextHolder.getRequestAttributes();
  ServletRequestAttributes servletRequestAttributes=(ServletRequestAttributes)requestAttributes;
  HttpServletRequest request=servletRequestAttributes.getRequest();
  UpmsLog upmsLog=new UpmsLog();
  Object result=pjp.proceed();
  Signature signature=pjp.getSignature();
  MethodSignature methodSignature=(MethodSignature)signature;
  Method method=methodSignature.getMethod();
  if (method.isAnnotationPresent(ApiOperation.class)) {
    ApiOperation log=method.getAnnotation(ApiOperation.class);
    upmsLog.setDescription(log.value());
  }
  if (method.isAnnotationPresent(RequiresPermissions.class)) {
    RequiresPermissions requiresPermissions=method.getAnnotation(RequiresPermissions.class);
    String[] permissions=requiresPermissions.value();
    if (permissions.length > 0) {
      upmsLog.setPermissions(permissions[0]);
    }
  }
  endTime=System.currentTimeMillis();
  LOGGER.debug("doAround>>>result={},???{}",result,endTime - startTime);
  upmsLog.setBasePath(RequestUtil.getBasePath(request));
  upmsLog.setIp(RequestUtil.getIpAddr(request));
  upmsLog.setMethod(request.getMethod());
  if ("GET".equalsIgnoreCase(request.getMethod())) {
    upmsLog.setParameter(request.getQueryString());
  }
 else {
    upmsLog.setParameter(ObjectUtils.toString(request.getParameterMap()));
  }
  upmsLog.setResult(JSON.toJSONString(result));
  upmsLog.setSpendTime((int)(endTime - startTime));
  upmsLog.setStartTime(startTime);
  upmsLog.setUri(request.getRequestURI());
  upmsLog.setUrl(ObjectUtils.toString(request.getRequestURL()));
  upmsLog.setUserAgent(request.getHeader("User-Agent"));
  upmsLog.setUsername(ObjectUtils.toString(request.getUserPrincipal()));
  upmsApiService.insertUpmsLogSelective(upmsLog);
  return result;
}
