@Transactional(rollbackFor=Exception.class) @Override public void add(List<User> users){
  int i=0;
  for (  User user : users) {
    userMapper.insert(user);
  }
}
