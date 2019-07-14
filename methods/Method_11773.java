private static boolean isAnyExcluded(Class<?> klass){
  ExcludeCategory annotation=klass.getAnnotation(ExcludeCategory.class);
  return annotation == null || annotation.matchAny();
}
