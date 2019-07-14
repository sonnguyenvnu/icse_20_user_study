public List<User> getUserByName(String name){
  return this.userDao.findByNameEquals(name);
}
