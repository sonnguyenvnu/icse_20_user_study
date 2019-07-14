@Override public <T>T newInstance(Class<T> beanClass,Class<? extends T> defaultClass){
  if (beanClass == null) {
    return null;
  }
  Mapper<T> mapper=realTypeMapper.get(beanClass);
  if (mapper != null) {
    return mapper.getInstanceGetter().get();
  }
  mapper=initCache(beanClass);
  if (mapper != null) {
    return mapper.getInstanceGetter().get();
  }
  if (defaultClass != null) {
    return newInstance(defaultClass);
  }
  if (Map.class == beanClass) {
    return (T)new HashMap<>();
  }
  if (List.class == beanClass) {
    return (T)new ArrayList<>();
  }
  if (Set.class == beanClass) {
    return (T)new HashSet<>();
  }
  throw new NotFoundException("can't create instance for " + beanClass);
}
