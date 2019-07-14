@Override protected boolean executeLogin(ServletRequest request,ServletResponse response){
  HttpServletRequest httpServletRequest=(HttpServletRequest)request;
  String token=httpServletRequest.getHeader(TOKEN);
  JWTToken jwtToken=new JWTToken(token);
  try {
    getSubject(request,response).login(jwtToken);
    return true;
  }
 catch (  Exception e) {
    log.error(e.getMessage());
    return false;
  }
}
