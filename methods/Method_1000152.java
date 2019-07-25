@GetMapping("/users/mybatis/insert") public Boolean insert(String name,String password){
  if (StringUtils.isEmpty(name) || StringUtils.isEmpty(password)) {
    return false;
  }
  User user=new User();
  user.setName(name);
  user.setPassword(password);
  return userDao.insertUser(user) > 0;
}
