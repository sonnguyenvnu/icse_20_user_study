public <T>MapperEntityFactory addMapping(Class<T> target,Mapper<? extends T> mapper){
  realTypeMapper.put(target,mapper);
  return this;
}
