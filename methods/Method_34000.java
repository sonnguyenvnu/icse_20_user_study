protected List<ConfigAttribute> findAttributes(Class<?> clazz){
  return processAnnotations(clazz.getAnnotations());
}
