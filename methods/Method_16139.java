protected AccessLoggerInfo createLogger(MethodInterceptorHolder holder){
  AccessLoggerInfo info=new AccessLoggerInfo();
  info.setId(IDGenerator.MD5.generate());
  info.setRequestTime(System.currentTimeMillis());
  LoggerDefine define=loggerParsers.stream().filter(parser -> parser.support(ClassUtils.getUserClass(holder.getTarget()),holder.getMethod())).findAny().map(parser -> parser.parse(holder)).orElse(null);
  if (define != null) {
    info.setAction(define.getAction());
    info.setDescribe(define.getDescribe());
  }
  info.setParameters(holder.getArgs());
  info.setTarget(holder.getTarget().getClass());
  info.setMethod(holder.getMethod());
  HttpServletRequest request=WebUtil.getHttpServletRequest();
  if (null != request) {
    info.setHttpHeaders(WebUtil.getHeaders(request));
    info.setIp(WebUtil.getIpAddr(request));
    info.setHttpMethod(request.getMethod());
    info.setUrl(request.getRequestURL().toString());
  }
  return info;
}
