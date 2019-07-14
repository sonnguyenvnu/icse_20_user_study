@Override public void touch(String token){
  SimpleUserToken userToken=tokenStorage.get(token);
  if (null != userToken) {
    userToken.touch();
    syncToken(userToken);
  }
}
