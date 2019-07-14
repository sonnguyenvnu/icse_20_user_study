private String getClassLevelFallback(Class<?> enclosingClass){
  if (enclosingClass.isAnnotationPresent(DefaultProperties.class)) {
    return enclosingClass.getAnnotation(DefaultProperties.class).defaultFallback();
  }
  return StringUtils.EMPTY;
}
