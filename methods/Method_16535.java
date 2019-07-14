protected Function<Object,String> createTargetIdGetter(Class entityClass,String... properties){
  String useProperty=null;
  for (  String property : properties) {
    Field field=ReflectionUtils.findField(entityClass,property);
    if (field != null) {
      useProperty=property;
    }
  }
  if (useProperty == null) {
    log.debug("?[{}]??????[{}],????????????.",entityClass,Arrays.asList(properties));
  }
  return entity -> {
    Map<String,String> userInfo=FastBeanCopier.copy(entity,new HashMap<>(),FastBeanCopier.include(properties));
    for (    String property : properties) {
      String value=userInfo.get(property);
      if (value != null) {
        return value;
      }
    }
    return null;
  }
;
}
