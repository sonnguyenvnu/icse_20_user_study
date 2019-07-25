@Override public User user(String account){
  User user=userMapper.getByAccount(account);
  if (null == user) {
    throw new CredentialsException();
  }
  if (!user.getStatus().equals(ManagerStatus.OK.getCode())) {
    throw new LockedAccountException();
  }
  return user;
}
