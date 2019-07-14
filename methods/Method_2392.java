@Override public Session createSession(SessionContext sessionContext){
  UpmsSession session=new UpmsSession();
  if (null != sessionContext && sessionContext instanceof WebSessionContext) {
    WebSessionContext webSessionContext=(WebSessionContext)sessionContext;
    HttpServletRequest request=(HttpServletRequest)webSessionContext.getServletRequest();
    if (null != request) {
      session.setHost(request.getRemoteAddr());
      session.setUserAgent(request.getHeader("User-Agent"));
    }
  }
  return session;
}
