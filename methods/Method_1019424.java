@Override public void create(User user){
  checkArgument(!(user == null || user.getUsername() == null),"User or username can't be null");
  managedUsers.put(user.getUsername(),user);
  logger.warn("Create a temporary user[{}]",user.getUsername());
}
