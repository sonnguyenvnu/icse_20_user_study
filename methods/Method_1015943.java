/** 
 * Handle flow.
 * @param request  the specified request
 * @param response the specified response
 * @return context
 */
public static RequestContext handle(final HttpServletRequest request,final HttpServletResponse response){
  try {
    request.setCharacterEncoding("UTF-8");
    response.setCharacterEncoding("UTF-8");
  }
 catch (  final Exception e) {
    LOGGER.log(Level.ERROR,"Sets request context character encoding failed",e);
  }
  final RequestContext ret=new RequestContext();
  ret.setRequest(request);
  ret.setResponse(response);
  Latkes.REQUEST_CONTEXT.set(ret);
  for (  final Handler handler : HANDLERS) {
    ret.addHandler(handler);
  }
  ret.handle();
  result(ret);
  Latkes.REQUEST_CONTEXT.set(null);
  return ret;
}
