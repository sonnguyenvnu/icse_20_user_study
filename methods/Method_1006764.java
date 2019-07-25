private List list(DataRepository dataRepository,Class<? extends BaseRepository> clzz){
  Type[] types=clzz.getGenericInterfaces();
  ParameterizedType parameterized=(ParameterizedType)types[0];
  Class clazz=(Class)parameterized.getActualTypeArguments()[0];
  return dataRepository.list(clazz);
}
