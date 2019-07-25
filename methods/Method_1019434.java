@Override public User find(String username){
  if (Strings.isNullOrEmpty(username)) {
    throw new IllegalArgumentException("Username can't be null or empty");
  }
  return users.get(username);
}
