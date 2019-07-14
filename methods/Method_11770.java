private static Set<Class<?>> getIncludedCategory(Class<?> klass){
  IncludeCategory annotation=klass.getAnnotation(IncludeCategory.class);
  return createSet(annotation == null ? null : annotation.value());
}
