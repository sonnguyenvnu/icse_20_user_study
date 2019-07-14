@Override public void signOutByUserId(String userId){
  if (null == userId) {
    return;
  }
  Set<String> tokens=getUserToken(userId);
  tokens.forEach(token -> signOutByToken(token,false));
  tokens.clear();
  userStorage.remove(userId);
}
