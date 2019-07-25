@Override public void update(User user){
  if (user == null) {
    throw new IllegalArgumentException("User can't null");
  }
  if (Strings.isNullOrEmpty(user.getUsername())) {
    throw new IllegalArgumentException("Username of the user can't be null or empty");
  }
  managedUsers.put(user.getUsername(),user);
  logger.warn("Update user[{}] temporarily",user.getUsername());
}
