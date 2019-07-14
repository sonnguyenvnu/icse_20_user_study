/** 
 * ???????
 */
@Override protected boolean preHandle(ServletRequest request,ServletResponse response) throws Exception {
  HttpServletRequest httpServletRequest=(HttpServletRequest)request;
  HttpServletResponse httpServletResponse=(HttpServletResponse)response;
  httpServletResponse.setHeader("Access-control-Allow-Origin",httpServletRequest.getHeader("Origin"));
  httpServletResponse.setHeader("Access-Control-Allow-Methods","GET,POST,OPTIONS,PUT,DELETE");
  httpServletResponse.setHeader("Access-Control-Allow-Headers",httpServletRequest.getHeader("Access-Control-Request-Headers"));
  if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
    httpServletResponse.setStatus(HttpStatus.OK.value());
    return false;
  }
  return super.preHandle(request,response);
}
