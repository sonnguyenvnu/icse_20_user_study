@Override public Object run(){
  RequestContext requestContext=RequestContext.getCurrentContext();
  HttpServletRequest request=requestContext.getRequest();
  String host=request.getRemoteHost();
  String method=request.getMethod();
  String uri=request.getRequestURI();
  log.info("??URI?{}?HTTP Method?{}???IP?{}",uri,method,host);
  return null;
}
