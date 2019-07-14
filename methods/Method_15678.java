private void signOutByToken(String token,boolean removeUserToken){
  if (token == null) {
    return;
  }
  SimpleUserToken tokenObject=tokenStorage.remove(token);
  if (tokenObject != null) {
    String userId=tokenObject.getUserId();
    if (removeUserToken) {
      Set<String> tokens=getUserToken(userId);
      if (!tokens.isEmpty()) {
        tokens.remove(token);
      }
      if (tokens.isEmpty()) {
        userStorage.remove(tokenObject.getUserId());
      }
    }
    publishEvent(new UserTokenRemovedEvent(tokenObject));
  }
}
