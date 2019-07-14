@Override public ParsedToken parseToken(HttpServletRequest request){
  HttpSession session=request.getSession(false);
  if (session != null) {
    String sessionId=session.getId();
    UserToken token=userTokenManager.getByToken(sessionId);
    long interval=session.getMaxInactiveInterval();
    if (token != null && token.isExpired()) {
      String userId=token.getUserId();
      return new AuthorizedToken(){
        @Override public String getUserId(){
          return userId;
        }
        @Override public String getToken(){
          return sessionId;
        }
        @Override public String getType(){
          return TOKEN_TYPE_SESSION_ID;
        }
        @Override public long getMaxInactiveInterval(){
          return interval;
        }
      }
;
    }
    return new ParsedToken(){
      @Override public String getToken(){
        return session.getId();
      }
      @Override public String getType(){
        return TOKEN_TYPE_SESSION_ID;
      }
    }
;
  }
  return null;
}
