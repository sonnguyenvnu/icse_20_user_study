@Override public boolean tokenIsLoggedIn(String token){
  if (token == null) {
    return false;
  }
  UserToken userToken=getByToken(token);
  return userToken != null && !userToken.isExpired();
}
