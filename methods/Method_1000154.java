@GetMapping("/users/mybatis/delete") public Boolean insert(Integer id){
  if (id == null || id < 1) {
    return false;
  }
  return userDao.delUser(id) > 0;
}
