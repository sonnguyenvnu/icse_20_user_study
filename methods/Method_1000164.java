@Override public int save(AdminUser user){
  user.setPassword(MD5Util.MD5Encode(user.getPassword(),"UTF-8"));
  return adminUserDao.addUser(user);
}
