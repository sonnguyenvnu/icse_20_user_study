@Override public User check(String username,String password){
  User user=find(username);
  if (user != null && user.getPassword() != null && user.getPassword().equals(password)) {
    return user;
  }
  return null;
}
