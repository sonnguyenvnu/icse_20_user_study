@Override public UserToken signIn(String token,String type,String userId,long maxInactiveInterval){
  SimpleUserToken detail=new SimpleUserToken(userId,token);
  detail.setType(type);
  detail.setMaxInactiveInterval(maxInactiveInterval);
  AllopatricLoginMode mode=allopatricLoginModes.getOrDefault(type,allopatricLoginMode);
  if (mode == AllopatricLoginMode.deny) {
    boolean hasAnotherToken=getByUserId(userId).stream().filter(userToken -> type.equals(userToken.getType())).map(SimpleUserToken.class::cast).peek(this::checkTimeout).anyMatch(UserToken::isNormal);
    if (hasAnotherToken) {
      throw new AccessDenyException("???????????");
    }
  }
 else   if (mode == AllopatricLoginMode.offlineOther) {
    List<UserToken> oldToken=getByUserId(userId);
    for (    UserToken userToken : oldToken) {
      if (type.equals(userToken.getType())) {
        changeTokenState(userToken.getToken(),TokenState.offline);
      }
    }
  }
  detail.setState(TokenState.normal);
  tokenStorage.put(token,detail);
  getUserToken(userId).add(token);
  publishEvent(new UserTokenCreatedEvent(detail));
  return detail;
}
