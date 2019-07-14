public List<News> getByMap(Map<String,Object> map){
  DynamicDataSourceContextHolder.resetDatabaseType();
  return newsDao.getByMap(map);
}
