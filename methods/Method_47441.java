public List<User> getByMap(Map<String,Object> map){
  DynamicDataSourceContextHolder.setDatabaseType(DatabaseTypeEnum.USER);
  return userDao.getByMap(map);
}
