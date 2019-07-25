@Override public Object run(){
  RequestContext ctx=RequestContext.getCurrentContext();
  HttpServletRequest request=ctx.getRequest();
  _log.info(String.format("%s request to %s",request.getMethod(),request.getRequestURL().toString()));
  Object accessToken=request.getParameter("accessToken");
  return null;
}
