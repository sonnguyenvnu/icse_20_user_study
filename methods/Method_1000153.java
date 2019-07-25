@GetMapping("/users/mybatis/update") public Boolean insert(Integer id,String name,String password){
  if (id == null || id < 1 || StringUtils.isEmpty(name) || StringUtils.isEmpty(password)) {
    return false;
  }
  User user=new User();
  user.setId(id);
  user.setName(name);
  user.setPassword(password);
  return userDao.updUser(user) > 0;
}
