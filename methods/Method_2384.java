@Override protected boolean onAccessDenied(ServletRequest request,ServletResponse response) throws Exception {
  getSubject(request,response).logout();
  String loginUrl=getLoginUrl() + (getLoginUrl().contains("?") ? "&" : "?") + "forceLogout=1";
  WebUtils.issueRedirect(request,response,loginUrl);
  return false;
}
