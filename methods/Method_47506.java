@Override public User getUserById(Long id){
  return userJpaDao.findOne(id);
}
