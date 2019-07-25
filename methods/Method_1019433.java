@Override public void update(User user){
  if (user == null) {
    throw new IllegalArgumentException("User can't null");
  }
  if (Strings.isNullOrEmpty(user.getUsername())) {
    throw new IllegalArgumentException("Username of the user can't be null or empty");
  }
  users.put(user.getUsername(),user);
}
