protected <T>Mapper<T> initCache(Class<T> beanClass){
  Mapper<T> mapper=null;
  Class<T> realType=null;
  ServiceLoader<T> serviceLoader=ServiceLoader.load(beanClass,this.getClass().getClassLoader());
  Iterator<T> iterator=serviceLoader.iterator();
  if (iterator.hasNext()) {
    realType=(Class<T>)iterator.next().getClass();
  }
  if (realType == null) {
    mapper=defaultMapperFactory.apply(beanClass);
  }
  if (!Modifier.isInterface(beanClass.getModifiers()) && !Modifier.isAbstract(beanClass.getModifiers())) {
    realType=beanClass;
  }
  if (mapper == null && realType != null) {
    if (logger.isDebugEnabled() && realType != beanClass) {
      logger.debug("use instance {} for {}",realType,beanClass);
    }
    mapper=new Mapper<>(realType,new DefaultInstanceGetter(realType));
  }
  if (mapper != null) {
    realTypeMapper.put(beanClass,mapper);
  }
  return mapper;
}
