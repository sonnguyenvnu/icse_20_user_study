public TLObject getUserOrChat(String username){
  if (username == null || username.length() == 0) {
    return null;
  }
  return objectsByUsernames.get(username.toLowerCase());
}
