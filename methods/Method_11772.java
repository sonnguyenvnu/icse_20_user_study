private static Set<Class<?>> getExcludedCategory(Class<?> klass){
  ExcludeCategory annotation=klass.getAnnotation(ExcludeCategory.class);
  return createSet(annotation == null ? null : annotation.value());
}
