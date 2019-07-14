@Override public boolean userIsLoggedIn(String userId){
  if (userId == null) {
    return false;
  }
  for (  UserToken userToken : getByUserId(userId)) {
    if (userToken.isNormal()) {
      return true;
    }
  }
  return false;
}
