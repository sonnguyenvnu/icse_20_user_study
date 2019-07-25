@Override public void create(User user){
  if (user == null) {
    throw new IllegalArgumentException("User can't be null");
  }
  if (Strings.isNullOrEmpty(user.getPassword())) {
    throw new IllegalArgumentException("username can't be null or empty");
  }
  users.put(user.getUsername(),user);
}
