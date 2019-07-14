@Override protected boolean isLoginAttempt(ServletRequest request,ServletResponse response){
  HttpServletRequest req=(HttpServletRequest)request;
  String token=req.getHeader(TOKEN);
  return token != null;
}
